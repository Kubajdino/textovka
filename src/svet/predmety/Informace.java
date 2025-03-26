package svet.predmety;

import hra.Hra;

public class Informace extends Predmet{
    private String inforamce;

    public Informace(String nazev, String inforamce) {
        super(nazev);
        this.inforamce = inforamce;
    }
    @Override
    public String pouzij(Hra hra) {
        return "";
    }
}
