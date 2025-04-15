package svet.npcs;

import hra.Hra;
import svet.predmety.Predmet;

public class Bojovnik  extends NPC{

    private int sila;
    private int zdravi;
    private int kritikal;

    public Bojovnik(String jmeno, String dialog, int sila, int zdravi, int kritikal) {
        super(jmeno, dialog);
        this.sila = sila;
        this.zdravi = zdravi;
        this.kritikal = kritikal;
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

    public int getKritikal() {
        return kritikal;
    }

    public void setZdravi(int zdravi) {
        this.zdravi = zdravi;
    }

    @Override
    public String mluv(Hra hra) {

        return getDialog();
    }
}
