package prikazy;

import hra.HerniSvet;
import hra.Hra;
import hra.Hrac;
import svet.Mistnost;
import svet.npcs.Bojovnik;
import svet.npcs.NPC;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Boj implements Command{



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
        Bojovnik bojovnik;

        if(!argument.equalsIgnoreCase(npc.getJmeno())){
            return "Nikdo takový tu není.";
        }else{
            if (!npc.getClass().equals(Bojovnik.class)) {
                return (mistnost.getNpc().getJmeno() + " s tebou nebude bojovat;)");
            }else{
                bojovnik = (Bojovnik)npc;
                System.out.println("Boj zacina proti " + mistnost.getNpc().getJmeno());
                System.out.println("Tvuj nepritel ma " + bojovnik.getZdravi() + "zivotu.");

                while(hrac.getZdravi() > 0 && bojovnik.getZdravi() > 0) {
                    System.out.println("Co chces udelat?");
                    System.out.println("1. utok");
                    System.out.println("2. obrana");
                    System.out.println("3. leceni");
                    System.out.println("4. utect");
                    System.out.print("> ");

                    int volba = scanner.nextInt();

                    switch (volba){
                        case 1:
                            utok(hrac, bojovnik);
                            break;
                        case 2:
                            obrana(hrac, bojovnik);
                            break;
                        case 3:

                            break;
                        case 4:
                            if(!utek(hrac, svet, bojovnik)){
                                break;
                            }
                            return "";

                        default:
                            System.out.println("Neplatna volba");
                            continue;
                }
            }
            if(hrac.getZdravi()>0){
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
        int zasah = (int) (Math.random() * 100);
        int sanceNaZasah = hrac.getPresnost();
        int sanceNaKrit = hrac.getKritikal();

        if (zasah < sanceNaZasah) {
            int dmg = hrac.getSila();
            if (Math.random() * 100 < sanceNaKrit) {
                dmg *= (int) 1.5;
                System.out.println("Kritický zásah!");
            }
            npc.setZdravi(npc.getZdravi()-dmg);
            System.out.println("Způsobil jsi " + dmg + " dmg.");
        } else {
            System.out.println("Zasah nevysel!");
        }
    }

     void obrana(Hrac hrac, Bojovnik npc) {
        long startTime = System.currentTimeMillis();
        long timeWindow = 2000; // 2 sekundy na reakci
        boolean obranaUspesna = false;

        System.out.println("Stiskni jakoukoliv klávesu pro obranu!");

        while (System.currentTimeMillis() < startTime + timeWindow) {
            if (isKeyPressed()) {
                obranaUspesna = true;
                break;
            }
        }

        if (obranaUspesna) {
            System.out.println("Obrana úspěšná! Counter útok!");
            npc.setZdravi(npc.getZdravi()-hrac.getSila());
        } else {
            System.out.println("Obrana neúspěšná! Dostal jsi plný damage.");
            hrac.setZdravi(hrac.getZdravi() - npc.getSila());
        }
    }

    // Metoda pro kontrolu stisku klávesy
     boolean isKeyPressed() {
        try {
            // Čte znak ze vstupu
            System.in.read();
            return true;
        } catch (IOException e) {
            return false;
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
}






