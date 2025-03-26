package svet.predmety;

import hra.Hra;

public class Zbran extends Predmet{
    private int sila;
    public Zbran(String nazev, int sila) {
        super(nazev);
        this.sila = sila;
    }

    @Override
    public String pouzij(Hra hra) {
        return "";
    }
}
