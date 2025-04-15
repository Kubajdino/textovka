package svet;

import svet.npcs.NPC;
import svet.predmety.Predmet;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída představuje místnost ve hře. Každá místnost má název, popis, sousedy, NPC (postavu) a předmět, který může hráč sebrat.
 */
public class Mistnost {
    private String nazev;  // Název místnosti
    private String popis;  // Popis místnosti
    private List<String> sousedi;  // Seznam názvů místností, které jsou sousedy této místnosti
    private NPC npc;  // NPC, které se nachází v místnosti (může být null, pokud není žádné)
    private Predmet predmet;  // Předmět, který se nachází v místnosti (může být null, pokud není žádný)

    /**
     * Konstruktor pro vytvoření místnosti bez NPC a předmětu.
     *
     * @param nazev Název místnosti.
     * @param popis Popis místnosti.
     * @param sousedi Seznam sousedních místností.
     */
    public Mistnost(String nazev, String popis, List<String> sousedi) {
        this.nazev = nazev;
        this.popis = popis;
        this.sousedi = sousedi;
    }

    /**
     * Konstruktor pro vytvoření místnosti s NPC a předmětem.
     *
     * @param nazev Název místnosti.
     * @param popis Popis místnosti.
     * @param sousedi Seznam sousedních místností.
     * @param npc NPC, které se nachází v místnosti.
     * @param predmet Předmět, který se nachází v místnosti.
     */
    public Mistnost(String nazev, String popis, List<String> sousedi, NPC npc, Predmet predmet) {
        this.nazev = nazev;
        this.popis = popis;
        this.sousedi = sousedi;
        this.npc = npc;
        this.predmet = predmet;
    }

    /**
     * Získání seznamu sousedních místností.
     *
     * @return Seznam názvů sousedních místností.
     */
    public List<String> getSousedi() {
        return sousedi;
    }

    /**
     * Získání názvu místnosti.
     *
     * @return Název místnosti.
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Získání NPC, které se nachází v místnosti.
     *
     * @return NPC v místnosti (může být null).
     */
    public NPC getNpc() {
        return npc;
    }

    /**
     * Nastavení NPC, které se nachází v místnosti.
     *
     * @param npc NPC, které se má nastavit.
     */
    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    /**
     * Získání popisu místnosti.
     *
     * @return Popis místnosti.
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Získání předmětu, který se nachází v místnosti.
     *
     * @return Předmět v místnosti (může být null).
     */
    public Predmet getPredmet() {
        return predmet;
    }

    /**
     * Nastavení předmětu, který se nachází v místnosti.
     *
     * @param predmet Předmět, který se má nastavit.
     */
    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
}
