package svet.npcs;

import hra.Hra;
import hra.Inventar;

/**
 * Třída reprezentující NPC postavu, která při interakci s hráčem předá předmět.
 * Tato postava poskytuje hráči předmět při interakci.
 */
public class SPredmetem extends NPC {

    /**
     * Konstruktor pro vytvoření postavy "SPredmetem" s názvem a dialogem.
     *
     * @param jmeno  Jméno NPC.
     * @param dialog Dialog, který NPC říká hráči při interakci.
     */
    public SPredmetem(String jmeno, String dialog) {
        super(jmeno, dialog);
    }

    /**
     * Implementace metody mluv pro postavu "SPredmetem".
     * Tato postava při interakci s hráčem předá předmět do inventáře hráče a zruší svůj předmět.
     *
     * @param hra Instance hry, která obsahuje aktuální stav hry.
     * @return Dialog, který NPC říká hráči při interakci.
     */
    @Override
    public String mluv(Hra hra) {
        Inventar inventar = hra.getInventar();

        // Pokud NPC má předmět, přidá ho do inventáře hráče a odstraní ho z NPC
        if (getPredmet() != null) {
            inventar.pridejPredmet(getPredmet());
            this.setPredmet(null);  // Po předání předmětu se předmět zruší
        }

        return getDialog();  // NPC řekne svůj dialog
    }
}
