package svet.predmety;

import hra.Hra;
import hra.Hrac;
import hra.Inventar;

public class Bonus extends Predmet{

    private int bonus;
    private int doba;
    private String typ;

    public Bonus(String nazev,int bonus, int doba, String typ) {
        super(nazev);
        this.bonus = bonus;
        this.doba = doba;
        this.typ = typ;
    }

    @Override
    public String pouzij(Hra hra) {
        Hrac hrac = hra.getHrac();
        Inventar inventar = hra.getInventar();
        if(typ.equalsIgnoreCase("zdravi")){
            hrac.pridatZdraviBonus(bonus,doba);
            inventar.vymazPredmet(this);
            return "Pridal sis bonus " + bonus + " hp na " + doba +" kol a nyni mas "+ hrac.getZdravi();
        } else if ((typ.equalsIgnoreCase("sila"))){
            hrac.pridatSilaBonus(bonus,doba);
            inventar.vymazPredmet(this);
            return "Pridal sis bonus " + bonus + " sily na " + doba +" kol a nyni mas "+ hrac.getSila();
        }else{
            return "Nic";
        }
    }
}

