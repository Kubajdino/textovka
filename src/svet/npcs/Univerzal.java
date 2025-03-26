package svet.npcs;

import hra.Hra;

public class Univerzal extends NPC{
    public Univerzal(String jmeno, String dialog) {
        super(jmeno, dialog);
    }

    @Override
    public String mluv(Hra hra) {
        return getDialog();
    }
}
