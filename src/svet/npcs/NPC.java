package svet.npcs;

import hra.Hra;

public abstract class NPC {
    private String jmeno;
    private String dialog;
    private int zdravi;
    private int sila;
    private int agresivita;

    public NPC(String jmeno, String dialog, int zdravi, int agresivita) {
        this.jmeno = jmeno;
        this.dialog = dialog;
        this.zdravi = zdravi;
        this.agresivita = agresivita;
    }

    public abstract String mluv(Hra hra);


    public String getDialog() {
        return dialog;
    }
    public String getJmeno() {
        return jmeno;
    }
    public int getZdravi() { return zdravi; }
    public int getAgresivita() { return agresivita; }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setZdravi(int zdravi) {
        this.zdravi = zdravi;
    }


    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }
}

