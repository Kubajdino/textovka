package svet.predmety;

import hra.Hra;
import hra.Hrac;

public class Zbran extends Predmet{

    private int sila;

    public Zbran(String nazev, int sila) {
        super(nazev);
        this.sila = sila;
    }

    @Override
    public String pouzij(Hra hra) {
        Hrac hrac = hra.getHrac();
        if(hrac.isZbran()) {
            return "Uz mas zbran";
        }else{
            hrac.setZbran(true);
            hrac.setSila(hrac.getSila()+sila);
            return "Zacal jsi pouzivat "+ getNazev();
        }
    }
}
