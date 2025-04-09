package svet.npcs;

import hra.Hra;

public abstract class NPC {
    private String jmeno;
    private String dialog;

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

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

}

