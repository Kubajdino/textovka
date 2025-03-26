package svet.predmety;

import hra.Hra;

public class Inforamce extends Predmet{
    private String inforamcel;

    public Inforamce(String nazev, String inforamcel) {
        super(nazev);
        this.inforamcel = inforamcel;
    }
    @Override
    public String pouzij(Hra hra) {
        return "";
    }
}
