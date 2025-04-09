package svet.npcs;

import hra.Hra;

public class Bojovnik  extends NPC{

    private int sila;
    private int zdravi;

    public Bojovnik(String jmeno, String dialog, int sila, int zdravi) {
        super(jmeno, dialog);
        this.sila = sila;
        this.zdravi = zdravi;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public int getZdravi() {
        return zdravi;
    }

    public void setZdravi(int zdravi) {
        this.zdravi = zdravi;
    }

    @Override
    public String mluv(Hra hra) {

        return getDialog();
    }
}
