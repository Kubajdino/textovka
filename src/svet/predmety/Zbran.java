package svet.predmety;

import hra.Hra;
import hra.Hrac;

/**
 * Třída představující zbraň, kterou může hráč získat a použít ve hře.
 * Zbraň přidává sílu hráči, pokud ji ještě nepoužívá.
 */
public class Zbran extends Predmet {

    private int sila;  // Síla, kterou zbraň přidává hráči

    /**
     * Konstruktor pro vytvoření zbraně s názvem a silou.
     *
     * @param nazev Název zbraně.
     * @param sila Síla, kterou zbraň přidává hráči.
     */
    public Zbran(String nazev, int sila) {
        super(nazev);
        this.sila = sila;
    }

    /**
     * Použití zbraně hráčem. Pokud hráč ještě žádnou zbraň nemá, zbraň mu přidá sílu.
     * Pokud už hráč má zbraň, zbraň nebude přidána znovu.
     *
     * @param hra Instance hry, která obsahuje stav hry, hráče a jeho inventář.
     * @return Zpráva o tom, zda byla zbraň přidána, nebo zda ji hráč již má.
     */
    @Override
    public String pouzij(Hra hra) {
        Hrac hrac = hra.getHrac();
        if (hrac.isZbran()) {
            return "Uz mas zbran";
        } else {
            hrac.setZbran(true);  // Nastaví, že hráč má zbraň
            hrac.setSila(hrac.getSila() + sila);  // Zvýší sílu hráče podle síly zbraně
            return "Zacal jsi pouzivat " + getNazev();
        }
    }
}
