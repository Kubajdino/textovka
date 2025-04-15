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

/**
 * Třída představující bojový systém ve hře.
 * Umožňuje hráči bojovat s NPC postavami, používat předměty a bránit se.
 */
public class Boj implements Command {

    boolean braniSeNPC = false;
    boolean braniSeHrac = false;

    /**
     * Konstruktor třídy Boj.
     */
    public Boj() {

    }

    /**
     * Metoda pro provedení boje mezi hráčem a NPC.
     *
     * @param argument Jméno NPC, se kterým chce hráč bojovat.
     * @param hra Instance aktuální hry.
     * @return Výsledek boje nebo chybová zpráva, pokud nebyl nalezen žádný vhodný NPC.
     */
    @Override
    public String execute(String argument, Hra hra) {

        Scanner scanner = new Scanner(System.in);
        Hrac hrac = hra.getHrac();
        HerniSvet svet = hra.getSvet();
        Mistnost mistnost = hrac.getAktualniMistnost();
        NPC npc = mistnost.getNpc();
        Inventar inventar = hra.getInventar();
        Bojovnik bojovnik;

        // Kontrola, zda bylo zadáno jméno NPC
        if (argument == null) return "Zadej s kym chces bojovat: " + mistnost.getNpc().getJmeno();

        // Ověření, zda NPC jméno odpovídá zadanému argumentu
        if (!argument.equalsIgnoreCase(npc.getJmeno())) {
            return "Nikdo takový tu není.";
        } else {
            if (!(npc instanceof Bojovnik)) {
                return (mistnost.getNpc().getJmeno() + " s tebou nebude bojovat;)");
            } else {
                bojovnik = (Bojovnik) npc;
                System.out.println("Boj zacina proti " + mistnost.getNpc().getJmeno());
                System.out.println("Tvuj nepritel ma " + bojovnik.getZdravi() + " zivotu.");

                // Cyklus boje
                while (hrac.getZdravi() > 0 && bojovnik.getZdravi() > 0) {
                    System.out.println("Co chces udelat?");
                    System.out.println("1. utok");
                    System.out.println("2. obrana");
                    System.out.println("3. pouzij predemt");
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

                    switch (volba) {
                        case 1:
                            if (bojovnik.getZdravi() > 0) NPCAI(bojovnik, hrac, hra);
                            utok(hrac, bojovnik);
                            break;
                        case 2:
                            braniSeHrac = true;
                            System.out.println("Branis se");
                            if (bojovnik.getZdravi() > 0) NPCAI(bojovnik, hrac, hra);
                            break;
                        case 3:
                            if (bojovnik.getZdravi() > 0) NPCAI(bojovnik, hrac, hra);
                            pouzijPredmet(inventar, hra, hrac);
                            break;
                        case 4:
                            if (bojovnik.getZdravi() > 0) NPCAI(bojovnik, hrac, hra);
                            if (!utek(hrac, svet, bojovnik)) {
                                break;
                            }
                            return "";

                        default:
                            System.out.println("Neplatna volba");
                            continue;
                    }

                    System.out.println("Mas " + hrac.getZdravi() + " hp \nNepritel ma " + bojovnik.getZdravi() + " hp");
                }

                // Výsledek boje
                if (hrac.getZdravi() > 0) {
                    mistnost.setNpc(null);
                    if (bojovnik.getPredmet() != null) {
                        inventar.pridejPredmet(bojovnik.getPredmet());
                        System.out.println("Nasel jsi u nej predmet " + bojovnik.getPredmet().getNazev());
                    }
                    return "Vyhral jsi nepritel je mrtev";
                } else {
                    System.out.println("Game over umrel jsi");
                    System.exit(0);
                    return null;
                }
            }
        }
    }

    /**
     * Provede útok hráče na NPC.
     *
     * @param hrac Instance hráče, který provádí útok.
     * @param npc Instance NPC, které je cílem útoku.
     */
    void utok(Hrac hrac, Bojovnik npc) {
        int sanceNaZasah = hrac.getPresnost();
        int sanceNaKrit = hrac.getKritikal();

        if (Math.random() * 100 <= sanceNaZasah) {
            double dmg = hrac.getSila();
            if (Math.random() * 100 <= sanceNaKrit) {
                dmg *= 1.5;
                System.out.println("Kritický zásah!");
            }
            if (braniSeNPC) dmg /= 2;
            npc.setZdravi(npc.getZdravi() - (int) dmg);
            System.out.println("-Způsobil jsi " + dmg + " dmg.");
        } else {
            System.out.println("Zasah nevysel!");
        }
    }

    /**
     * Provádí obranu hráče proti útoku NPC.
     *
     * @param hrac Instance hráče, který se brání.
     * @param npc Instance NPC, které provádí útok.
     */
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
        if (!braniSeNPC) {
            if (obranaUspesna.get()) {
                System.out.println("Obrana úspěšná! Counter útok!");
                npc.setZdravi(npc.getZdravi() - hrac.getSila());
            } else {
                System.out.println("Obrana neúspěšná! Dostal jsi, ale jen polovicni dmg.");
                hrac.setZdravi(hrac.getZdravi() - npc.getSila() / 2);
            }
        }

        inputThread.interrupt();
    }

    /**
     * Použije předmět z inventáře během boje.
     *
     * @param inventar Inventář hráče obsahující předměty.
     * @param hra Instance hry.
     * @param hrac Instance hráče, který používá předmět.
     */
    void pouzijPredmet(Inventar inventar, Hra hra, Hrac hrac) {
        System.out.print("Jaky predmet chces pouzit: ");
        for (Predmet p : inventar.getInventar()) System.out.print(p + ", ");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String vstup = scanner.nextLine();

        Predmet predmet = inventar.najdipredmet(vstup);
        if (predmet != null) {
            System.out.println(predmet.pouzij(hra));
        } else {
            System.out.println("Tento predmet nemas");
        }
        hrac.snizitSilaBonusDoba();
        hrac.snizitZdraviBonusDoba();
    }


    /**
     * Pokusí se o útěk hráče z boje.
     *
     * @param hrac Instance hráče, který se pokouší utéct.
     * @param svet Herní svět, kde se boj odehrává.
     * @param npc NPC, které se účastní boje.
     * @return true pokud se útěk povedl, false pokud ne.
     */
    boolean utek(Hrac hrac, HerniSvet svet, Bojovnik npc) {
        int sanceNaUtek = (int) (Math.random() * 100) - (50 - hrac.getZdravi());

        if (sanceNaUtek > 50) {
            System.out.println("Utekl jsi");
            Random random = new Random();
            int index = random.nextInt(hrac.getAktualniMistnost().getSousedi().size());
            System.out.println(hrac.presunSe(hrac.getAktualniMistnost().getSousedi().get(index), svet));
            return true;
        } else {
            hrac.setZdravi(hrac.getZdravi() - npc.getSila() * 2);
            System.out.println("Nepovedlo se ti to a jeste jsi dostal ranu do zad");
            return false;
        }
    }

    /**
     * AI logika pro chování NPC během boje.
     *
     * @param bojovnik NPC postava, která bojuje.
     * @param hrac Instance hráče, proti kterému NPC bojuje.
     * @param hra Instance hry.
     */
    void NPCAI(Bojovnik bojovnik, Hrac hrac, Hra hra) {
        Random rand = new Random();

        if (bojovnik.getZdravi() < 20 && rand.nextInt(100) < 50) {
            bojovnik.setZdravi(bojovnik.getZdravi() + 20);
            System.out.println(bojovnik.getJmeno() + " použil léčivý lektvar! Nyni ma " + bojovnik.getZdravi());
            return;
        }
        int volba = rand.nextInt(2);

        switch (volba) {
            case 0:
                // Útok na hráče
                System.out.println(bojovnik.getJmeno() + " útočí!");
                if (braniSeHrac) {
                    obrana(hrac, bojovnik);
                } else {
                    int dmg = bojovnik.getSila();
                    if (rand.nextInt(100) < bojovnik.getKritikal()) dmg *= 2;
                    hrac.setZdravi(hrac.getZdravi() - dmg);
                    System.out.println("-Dostal jsi " + dmg + " dmg!");
                }
                break;
            case 1:
                System.out.println(bojovnik.getJmeno() + " se brání!");
                braniSeNPC = true;
                if (braniSeHrac) {
                    System.out.println("Oba se bráníte, žádné poškození!");
                }
                break;
        }
    }
}
