package hra;

import prikazy.*;
import svet.predmety.Informace;

import java.util.Scanner;

public class Hra {
    private HerniSvet svet;
    private Hrac hrac;
    private Inventar inventar;
    private SeznamPrikazu prikazy;

    public Hra() {
        svet = new HerniSvet();
        hrac = new Hrac(svet.getMistnost("hrad"));
        inventar = new Inventar();
        prikazy = new SeznamPrikazu();
        inicializujPrikazy();
    }

    private void inicializujPrikazy() {
        prikazy.addCommand("jdi <směr>","jdi", new Jdi());
        prikazy.addCommand("vezmi <objekt>","vezmi", new Vezmi());
        prikazy.addCommand("vypis inventar","vypis", new OtevriInventar());
        prikazy.addCommand("mluv <NPC>","mluv", new Mluv());
        prikazy.addCommand("pomoc","pomoc", new Pomoc());
        prikazy.addCommand("konec","konec", new Konec());
        prikazy.addCommand("boj <NPC>","boj", new Boj());
        prikazy.addCommand("pouzij <Predmet>","pouzij",new Pouzij());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nVítej ve hre "+ svet.getJmenoHry() +"\n\nPouzij tyto prikazy: ");
        System.out.println(prikazy.vypisSeznamPrikazu());
        System.out.println(hrac.vypisObsahMistnosti());

        while (true) {
            System.out.print("> ");
            String vstup = scanner.nextLine().toLowerCase();
            String[] casti = vstup.split(" ", 2);
            String prikaz = casti[0];
            String argument = (casti.length > 1) ? casti[1] : null;

            if (prikazy.getSezanmPrikazu().containsKey(prikaz)) {
                System.out.println(prikazy.getSezanmPrikazu().get(prikaz).execute(argument, this));

            } else {
                System.out.println("Neznámý příkaz.");
            }
        }
    }

    public HerniSvet getSvet() {
        return svet;
    }

    public void setSvet(HerniSvet svet) {
        this.svet = svet;
    }

    public Hrac getHrac() {
        return hrac;
    }

    public void setHrac(Hrac hrac) {
        this.hrac = hrac;
    }

    public Inventar getInventar() {
        return inventar;
    }

    public void setInventar(Inventar inventar) {
        this.inventar = inventar;
    }

    public SeznamPrikazu getPrikazy() {
        return prikazy;
    }

    public void setPrikazy(SeznamPrikazu prikazy) {
        this.prikazy = prikazy;
    }
}
