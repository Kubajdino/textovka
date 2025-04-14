package prikazy;

import hra.HerniSvet;
import hra.Hra;
import hra.Hrac;
import hra.Inventar;
import svet.Mistnost;
import svet.npcs.Bojovnik;
import svet.npcs.NPC;
import svet.predmety.Predmet;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Boj implements Command{

    boolean braniSeNPC = false;
    boolean braniSeHrac = false;



    public Boj() {

    }
    @Override
    public String execute(String argument, Hra hra){
        if(argument == null) return "Zadej s kym chces bojovat";
        Scanner scanner = new Scanner(System.in);
        Hrac hrac = hra.getHrac();
        HerniSvet svet = hra.getSvet();
        Mistnost mistnost = hrac.getAktualniMistnost();
        NPC npc = mistnost.getNpc();
        Inventar inventar = hra.getInventar();
        Bojovnik bojovnik;

        if(!argument.equalsIgnoreCase(npc.getJmeno())){
            return "Nikdo takový tu není.";
        }else{
            if (!(npc instanceof Bojovnik)) {
                return (mistnost.getNpc().getJmeno() + " s tebou nebude bojovat;)");
            }else{
                bojovnik = (Bojovnik)npc;
                System.out.println("Boj zacina proti " + mistnost.getNpc().getJmeno());
                System.out.println("Tvuj nepritel ma " + bojovnik.getZdravi() + " zivotu.");

                while(hrac.getZdravi() > 0 && bojovnik.getZdravi() > 0) {
                    System.out.println("Co chces udelat?");
                    System.out.println("1. utok");
                    System.out.println("2. obrana");
                    System.out.println("3. leceni");
                    System.out.println("4. utect");
                    System.out.print("> ");

                    braniSeNPC = false;
                    braniSeHrac = false;

                    int volba;
                    try {
                        volba = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Neplatna volba");
                        scanner.nextLine();
                        continue;
                    }

                    switch (volba){
                        case 1:
                            if(bojovnik.getZdravi() > 0)NPCAI(bojovnik,hrac,hra);
                            utok(hrac, bojovnik);
                            break;
                        case 2:
                            braniSeHrac = true;
                            System.out.println("Branis se");
                            if(bojovnik.getZdravi() > 0)NPCAI(bojovnik,hrac,hra);
                            break;
                        case 3:
                            if(bojovnik.getZdravi() > 0)NPCAI(bojovnik,hrac,hra);
                            leceni(hrac, inventar, hra);
                            break;
                        case 4:
                            if(bojovnik.getZdravi() > 0)NPCAI(bojovnik,hrac,hra);
                            if(!utek(hrac, svet, bojovnik)){
                                break;
                            }
                            return "";

                        default:
                            System.out.println("Neplatna volba");
                            continue;
                }

                System.out.println("Mas "+hrac.getZdravi()+" hp \nNepritel ma "+ bojovnik.getZdravi() +" hp");
                }
                if(hrac.getZdravi()>0){
                mistnost.setNpc(null);
                return "Vyhral jsi nepritel je mrtev";
            }
            else{
                System.out.println("Game over umrel jsi");
                System.exit(0);
                return null;
            }
            }
        }
    }

     void utok(Hrac hrac, Bojovnik npc){
        int sanceNaZasah = hrac.getPresnost();
        int sanceNaKrit = hrac.getKritikal();

        if (Math.random() * 100 <= sanceNaZasah) {
            double dmg = hrac.getSila();
            if (Math.random() * 100 <= sanceNaKrit) {
                dmg *= 1.5;
                System.out.println("Kritický zásah!");
            }
            if(braniSeNPC) dmg /= 2;
            npc.setZdravi(npc.getZdravi()-(int)dmg);
            System.out.println("-Způsobil jsi " + dmg + " dmg.");
        } else {
            System.out.println("Zasah nevysel!");
        }
    }

     void obrana(Hrac hrac, Bojovnik npc) {
        AtomicBoolean obranaUspesna = new AtomicBoolean(false);

        Thread inputThread = new Thread(() -> {
            try {
                int input = System.in.read();
                char ch = (char) input;
                if (ch == 'w' || ch == 'a' || ch == 's' || ch == 'd') {
                    obranaUspesna.set(true);
                }
            } catch (IOException e) {
                // chyba při čtení
            }
        });

        inputThread.start();

        System.out.println("Rychle stiskni klávesu W, S, A, D pro obranu! Máš 2 sekundy!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // přerušení spánku
        }
        if(!braniSeNPC) {
            if (obranaUspesna.get()) {
                System.out.println("Obrana úspěšná! Counter útok!");
                npc.setZdravi(npc.getZdravi() - hrac.getSila());
            } else {
                System.out.println("Obrana neúspěšná! Dostal jsi, ale jen polovicni damage.");
                hrac.setZdravi(hrac.getZdravi() - npc.getSila() / 2);
            }
        }

        inputThread.interrupt();
    }

    void leceni(Hrac hrac, Inventar inventar, Hra hra) {
        Predmet p = inventar.najdiLektvarZdravi();
        if (p != null) {
            System.out.println(p.pouzij(hra));
            inventar.vymazPredmet(p);
        } else {
            System.out.println("Nemáš žádný léčivý lektvar!");
        }
    }



    boolean utek(Hrac hrac, HerniSvet svet, Bojovnik npc){
        int sanceNaUtek = (int) (Math.random() * 100) - (50 - hrac.getZdravi());

        if(sanceNaUtek > 50){
            System.out.println("Utekl jsi");
            Random random = new Random();
            int index = random.nextInt(hrac.getAktualniMistnost().getSousedi().size());
            System.out.println(hrac.presunSe(hrac.getAktualniMistnost().getSousedi().get(index), svet));
            return true;
        }else{
            hrac.setZdravi(hrac.getZdravi()- npc.getSila()*2);
            System.out.println("Nepovedlo se ti to a jeste jsi dostal ranu do zad");
            return false;
        }

    }



    void NPCAI(Bojovnik bojovnik, Hrac hrac, Hra hra){
        Random rand = new Random();

        if (bojovnik.getZdravi() < 20 && rand.nextInt(100) < 50) {
            bojovnik.setZdravi(bojovnik.getZdravi()+20);
            System.out.println(bojovnik.getJmeno() + " použil léčivý lektvar! Nyni ma "+bojovnik.getZdravi());
            return;
        }
        int volba = rand.nextInt(2);

        switch (volba) {
            case 0:
                // Útok na hráče
                System.out.println(bojovnik.getJmeno() + " útočí!");
                if(braniSeHrac){
                    obrana(hrac,bojovnik);
                }else {
                    int dmg = bojovnik.getSila();
                    if(rand.nextInt(100)<bojovnik.getKritikal())dmg*=2;
                    hrac.setZdravi(hrac.getZdravi() - dmg);
                    System.out.println("-Dostal jsi " + dmg + " poškození!");
                }
                break;
            case 1:
                System.out.println(bojovnik.getJmeno() +" se brání!");
                braniSeNPC = true;
                if (braniSeHrac) {
                    System.out.println("Oba se bráníte, žádné poškození!");
                }
                break;
        }
    }
}








