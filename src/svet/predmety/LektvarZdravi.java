package svet.predmety;

import hra.Hra;
import hra.Hrac;
import hra.Inventar;

public class LektvarZdravi extends Predmet{
    private int zdravi;
    public LektvarZdravi(String nazev, int zdravi) {
        super(nazev);
        this.zdravi = zdravi;
    }

    @Override
    public String pouzij(Hra hra) {
        Hrac hrac = hra.getHrac();
        Inventar inventar = hra.getInventar();
        hrac.setZdravi(Math.min(hrac.getZdravi() + zdravi, 100));
        inventar.vymazPredmet(this);
        return "Vylecil ses o " + zdravi + " hp a nyni mas "+ hrac.getZdravi();
    }
}
