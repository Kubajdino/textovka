package svet.predmety;

import hra.Hra;
import hra.Hrac;

public class LektvarZdravi extends Predmet{
    private int zdravi;
    public LektvarZdravi(String nazev, int zdravi) {
        super(nazev);
        this.zdravi = zdravi;
    }

    @Override
    public String pouzij(Hra hra) {
        Hrac hrac = hra.getHrac();
        hrac.setZdravi(hrac.getZdravi()+zdravi);
        return "Vylecil ses o " + zdravi + " hp a nyni mas "+ hrac.getZdravi();
    }
}
