package svet.npcs;

import hra.Hra;

public class Bojovnik  extends NPC{

    private int sila;
    private int zivoty;

    public Bojovnik(String jmeno, String dialog) {
        super(jmeno, dialog);
    }

    @Override
    public String mluv(Hra hra) {

        return null;
    }
}
