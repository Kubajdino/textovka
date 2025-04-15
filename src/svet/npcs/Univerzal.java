package svet.npcs;

import hra.Hra;

/**
 * Třída reprezentující univerzální NPC postavu, která při interakci s hráčem pouze vypíše svůj dialog.
 * Tato postava nemá žádné specifické chování nebo předměty, které by předávala hráči.
 */
public class Univerzal extends NPC {

    /**
     * Konstruktor pro vytvoření postavy "Univerzal" s názvem a dialogem.
     *
     * @param jmeno  Jméno NPC.
     * @param dialog Dialog, který NPC říká hráči při interakci.
     */
    public Univerzal(String jmeno, String dialog) {
        super(jmeno, dialog);
    }

    /**
     * Implementace metody mluv pro postavu "Univerzal".
     * Tato postava při interakci s hráčem pouze vypíše svůj dialog.
     *
     * @param hra Instance hry, která obsahuje aktuální stav hry.
     * @return Dialog, který NPC říká hráči při interakci.
     */
    @Override
    public String mluv(Hra hra) {
        return getDialog();  // NPC pouze vypíše svůj dialog
    }
}
