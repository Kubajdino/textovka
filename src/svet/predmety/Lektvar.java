package svet.predmety;

import hra.Hra;

public class Lektvar extends Predmet{
    private int zdravi;
    public Lektvar(String nazev, int zdravi) {
        super(nazev);
        this.zdravi = zdravi;
    }

    @Override
    public String pouzij(Hra hra) {
        return "";
    }
}
