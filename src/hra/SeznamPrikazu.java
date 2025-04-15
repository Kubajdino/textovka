package hra;

import prikazy.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Třída {@code SeznamPrikazu} uchovává mapu příkazů a jejich tvarů zadání.
 */
public class SeznamPrikazu {

    /** Mapa příkazů, kde klíčem je název příkazu a hodnotou je objekt příkazu. */
    HashMap<String, Command> sezanmPrikazu = new HashMap<>();

    /** Seznam tvarů zadání příkazů, určených pro nápovědu hráči. */
    List<String> tvaryZadani = new ArrayList<>();

    /**
     * Přidá nový příkaz do seznamu.
     *
     * @param tvarZadani tvar příkazu, jak ho zadává hráč (např. "vezmi <objekt>")
     * @param prikaz     klíčové slovo příkazu (např. "vezmi")
     * @param command    objekt implementující logiku příkazu
     */
    public void addCommand(String tvarZadani, String prikaz, Command command) {
        sezanmPrikazu.put(prikaz, command);
        tvaryZadani.add(tvarZadani);
    }

    /**
     * Vrací seznam všech dostupných tvarů příkazů jako nápovědu.
     *
     * @return formátovaný výpis příkazů
     */
    public String vypisSeznamPrikazu() {
        StringBuilder sb = new StringBuilder();
        for (String prikaz : tvaryZadani) {
            sb.append("\t").append(prikaz).append("\n");
        }
        return sb.toString();
    }

    /**
     * Vrací mapu všech zaregistrovaných příkazů.
     *
     * @return mapa příkazů
     */
    public HashMap<String, Command> getSezanmPrikazu() {
        return sezanmPrikazu;
    }
}
