package prikazy;

import hra.HerniSvet;
import hra.Hra;
import hra.Hrac;
import svet.Mistnost;
import svet.npcs.NPC;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Boj implements Command{



    public Boj() {

    }
    @Override
    public String execute(String argument, Hra hra){
        Scanner scanner = new Scanner(System.in);
        Hrac hrac = hra.getHrac();
        HerniSvet svet = hra.getSvet();
            Mistnost mistnost = hrac.getAktualniMistnost();
        if(mistnost.getNpc().getAgresivita()>0){
            while(hrac.getZdravi() > 0 && mistnost.getNpc().getZdravi() > 0) {
                System.out.println("Boj zacina proti " + mistnost.getNpc().getJmeno());
                System.out.println("Co chces udelat?");
                System.out.println("1. utok");
                System.out.println("2. obrana");
                System.out.println("3. leceni");
                System.out.println("4. utect");

                int volba = scanner.nextInt();

                switch (volba){
                    case 1:
                        utok(hrac, hrac.getAktualniMistnost().getNpc());
                        break;
                    case 2:
                        obrana(hrac, hrac.getAktualniMistnost().getNpc());
                        break;
                    case 3:

                        break;
                    case 4:
                        utek(hrac, svet, hrac.getAktualniMistnost().getNpc());
                        break;

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

        }else{
            return (mistnost.getNpc().getJmeno() + " s tebou nebude bojovat;)");
        }
    }

    private void utok(Hrac hrac, NPC npc){
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

    private void obrana(Hrac hrac, NPC npc) {
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
    private boolean isKeyPressed() {
        try {
            // Čte znak ze vstupu
            System.in.read();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    private void utek(Hrac hrac, HerniSvet svet, NPC npc){
        int sanceNaUtek = (int) (Math.random() * 100) - (50 - hrac.getZdravi());

        if(sanceNaUtek > 50){
            System.out.println("Utekl jsi");
            Random random = new Random();
            int index = random.nextInt(hrac.getAktualniMistnost().getSousedi().size());
            hrac.presunSe(hrac.getAktualniMistnost().getSousedi().get(index), svet);
        }else{
            hrac.setZdravi(hrac.getZdravi()- npc.getSila()*2);
            System.out.println("Nepovedlo se ti to a jeste jsi dostal ranu do zad");
        }

    }
}






