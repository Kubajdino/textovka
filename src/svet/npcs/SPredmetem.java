package svet.npcs;

import hra.Hra;
import hra.Inventar;

public class SPredmetem extends NPC{

    public SPredmetem(String jmeno, String dialog) {

        super(jmeno, dialog);
    }

    @Override
    public String mluv(Hra hra) {
        Inventar inventar = hra.getInventar();
        if(getPredmet() != null)inventar.pridejPredmet(getPredmet());
        this.setPredmet(null);
        return getDialog();
    }
}
