package svet.predmety;

import hra.Hra;

/**
 * Třída představující předmět, který hráči poskytuje nějakou informaci po jeho použití.
 */
public class Informace extends Predmet {

    private String inforamce;  // Informace, kterou předmět poskytuje hráči

    /**
     * Konstruktor pro vytvoření předmětu s informací.
     *
     * @param nazev Název předmětu.
     * @param inforamce Informace, kterou předmět poskytuje při použití.
     */
    public Informace(String nazev, String inforamce) {
        super(nazev);
        this.inforamce = inforamce;
    }

    /**
     * Použití předmětu s informací. Tato metoda vrátí textovou zprávu s informací, kterou hráč zjistí
     * po použití předmětu.
     *
     * @param hra Instance hry, která obsahuje hráče a jeho stav.
     * @return Zpráva s informací, kterou hráč získal.
     */
    @Override
    public String pouzij(Hra hra) {
        return "Zjistil jsi ze: " + inforamce;
    }
}
