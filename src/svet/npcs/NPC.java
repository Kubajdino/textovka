package svet.npcs;

import hra.Hra;
import svet.predmety.Predmet;

public abstract class NPC {
    private String jmeno;
    private String dialog;
    private Predmet predmet;

    public NPC(String jmeno, String dialog) {
        this.jmeno = jmeno;
        this.dialog = dialog;
    }

    public abstract String mluv(Hra hra);


    public String getDialog() {
        return dialog;
    }
    public String getJmeno() {
        return jmeno;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public Predmet getPredmet() {
        return predmet;
    }
}

