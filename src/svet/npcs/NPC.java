package svet.npcs;

import hra.Hra;
import svet.predmety.Predmet;

/**
 * Abstraktní třída představující postavu v hře (NPC).
 * Třída poskytuje základní vlastnosti a chování pro všechny nehratelné postavy (NPC).
 * Každý typ NPC musí implementovat metodu mluv, která definuje, jak NPC reaguje na interakci s hráčem.
 */
public abstract class NPC {
    private String jmeno;       // Jméno NPC.
    private String dialog;      // Dialog, který NPC říká hráči.
    private Predmet predmet;    // Předmět, který NPC může dát hráči.

    /**
     * Konstruktor pro vytvoření NPC s názvem a dialogem.
     *
     * @param jmeno  Jméno NPC.
     * @param dialog Dialog, který NPC říká hráči při interakci.
     */
    public NPC(String jmeno, String dialog) {
        this.jmeno = jmeno;
        this.dialog = dialog;
    }

    /**
     * Abstraktní metoda pro implementaci interakce s hráčem.
     * Každý typ NPC musí tuto metodu implementovat a definovat chování, jak reaguje na interakci s hráčem.
     *
     * @param hra Instance hry, která obsahuje aktuální stav hry.
     * @return Text, který popisuje výsledek interakce s NPC.
     */
    public abstract String mluv(Hra hra);

    /**
     * Vrací dialog, který NPC říká hráči při interakci.
     *
     * @return Dialog NPC.
     */
    public String getDialog() {
        return dialog;
    }

    /**
     * Vrací jméno NPC.
     *
     * @return Jméno NPC.
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Nastavuje předmět, který NPC může dát hráči.
     *
     * @param predmet Předmět, který NPC může dát hráči.
     */
    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    /**
     * Nastavuje jméno NPC.
     *
     * @param jmeno Nové jméno NPC.
     */
    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    /**
     * Vrací předmět, který NPC může dát hráči.
     *
     * @return Předmět NPC.
     */
    public Predmet getPredmet() {
        return predmet;
    }
}
