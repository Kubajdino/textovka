package svet.predmety;

import hra.Hra;

public abstract class Predmet {
    private String nazev;

    public Predmet(String nazev) {
        this.nazev = nazev;
    }

    public abstract String pouzij(Hra hra);

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
}
