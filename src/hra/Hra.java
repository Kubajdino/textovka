package hra;

import prikazy.*;
import svet.predmety.Informace;

import java.util.Scanner;

/**
 * Třída {@code Hra} představuje hlavní logiku textové hry.
 * Obsahuje inicializaci světa, hráče, inventáře a seznamu příkazů.
 * Metoda {@code run()} spouští hlavní herní smyčku.
 */
public class Hra {
    /** Herní svět, ve kterém se hra odehrává. */
    private HerniSvet svet;

    /** Objekt reprezentující hráče. */
    private Hrac hrac;

    /** Inventář hráče. */
    private Inventar inventar;

    /** Seznam dostupných příkazů. */
    private SeznamPrikazu prikazy;

    /**
     * Konstruktor, který inicializuje všechny části hry.
     */
    public Hra() {
        svet = new HerniSvet();
        hrac = new Hrac(svet.getMistnost("hrad"));
        inventar = new Inventar();
        prikazy = new SeznamPrikazu();
        inicializujPrikazy();
    }

    /**
     * Přidá dostupné příkazy do seznamu příkazů.
     */
    private void inicializujPrikazy() {
        prikazy.addCommand("jdi <směr>", "jdi", new Jdi());
        prikazy.addCommand("vezmi <objekt>", "vezmi", new Vezmi());
        prikazy.addCommand("vypis inventar", "vypis", new OtevriInventar());
        prikazy.addCommand("mluv <NPC>", "mluv", new Mluv());
        prikazy.addCommand("pomoc", "pomoc", new Pomoc());
        prikazy.addCommand("konec", "konec", new Konec());
        prikazy.addCommand("boj <NPC>", "boj", new Boj());
        prikazy.addCommand("pouzij <Predmet>", "pouzij", new Pouzij());
    }

    /**
     * Spustí hlavní herní smyčku, která zpracovává příkazy od uživatele.
     *
     * @return zpráva o konci hry
     */
    public String run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nVítej ve hre " + svet.getJmenoHry() + "\n\nPouzij tyto prikazy: ");
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

            if (inventar.najdipredmet("konec") != null) return "Konec hry. Malagar byl porazen";
        }
    }

    /**
     * Vrací herní svět.
     *
     * @return objekt {@link HerniSvet}
     */
    public HerniSvet getSvet() {
        return svet;
    }

    /**
     * Nastaví nový herní svět.
     *
     * @param svet nový objekt {@link HerniSvet}
     */
    public void setSvet(HerniSvet svet) {
        this.svet = svet;
    }

    /**
     * Vrací hráče.
     *
     * @return objekt {@link Hrac}
     */
    public Hrac getHrac() {
        return hrac;
    }

    /**
     * Nastaví hráče.
     *
     * @param hrac nový objekt {@link Hrac}
     */
    public void setHrac(Hrac hrac) {
        this.hrac = hrac;
    }

    /**
     * Vrací inventář hráče.
     *
     * @return objekt {@link Inventar}
     */
    public Inventar getInventar() {
        return inventar;
    }

    /**
     * Nastaví inventář hráče.
     *
     * @param inventar nový objekt {@link Inventar}
     */
    public void setInventar(Inventar inventar) {
        this.inventar = inventar;
    }

    /**
     * Vrací seznam příkazů.
     *
     * @return objekt {@link SeznamPrikazu}
     */
    public SeznamPrikazu getPrikazy() {
        return prikazy;
    }

    /**
     * Nastaví seznam příkazů.
     *
     * @param prikazy nový objekt {@link SeznamPrikazu}
     */
    public void setPrikazy(SeznamPrikazu prikazy) {
        this.prikazy = prikazy;
    }
}
