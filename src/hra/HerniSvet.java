package hra;

import svet.Mistnost;
import svet.predmety.Predmet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Třída {@code HerniSvet} reprezentuje herní svět hry.
 * Načítá místnosti, NPC a předměty ze souborů a poskytuje přístup k těmto datům.
 */
public class HerniSvet {
    /** Mapa názvů místností na objekty {@link Mistnost}. */
    private Map<String, Mistnost> mistnosti;

    /** Obecné informace o hře načtené ze souboru. */
    String[] informace;

    /** Název hry. */
    private String jmenoHry;

    /**
     * Konstruktor, který inicializuje herní svět načtením dat ze souborů.
     * Načítá mapu místností, NPC a předměty.
     */
    public HerniSvet() {
        mistnosti = utils.NacitacSouboru.nactiMistnostiZeSouboru("files/mapa.json");
        utils.NacitacSouboru.nactiNPC(mistnosti, "files/npc.json");
        utils.NacitacSouboru.nactiPredmet(mistnosti, "files/predmety.json");
        informace = utils.NacitacSouboru.nactiObecneInfo("files/obecneInfo.txt");
        jmenoHry = informace[0];
    }

    /**
     * Vrátí objekt {@link Mistnost} podle jeho názvu.
     *
     * @param nazev název místnosti
     * @return objekt {@link Mistnost} nebo {@code null}, pokud nebyla nalezena
     */
    public Mistnost getMistnost(String nazev) {
        return mistnosti.get(nazev);
    }

    /**
     * Vrátí název hry.
     *
     * @return název hry
     */
    public String getJmenoHry() {
        return jmenoHry;
    }
}
