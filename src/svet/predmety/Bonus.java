package svet.predmety;

import hra.Hra;
import hra.Hrac;
import hra.Inventar;

/**
 * Třída reprezentující bonusový předmět, který poskytuje hráči dočasné zlepšení jeho schopností,
 * jako například zvýšení zdraví nebo síly na určitý počet kol.
 */
public class Bonus extends Predmet {

    private int bonus;    // Hodnota bonusu, který se přidá hráči
    private int doba;     // Doba trvání bonusu v kolech
    private String typ;   // Typ bonusu (zdraví, síla)

    /**
     * Konstruktor pro vytvoření bonusového předmětu.
     *
     * @param nazev Název předmětu.
     * @param bonus Hodnota bonusu, který bude hráči přidán.
     * @param doba Doba trvání bonusu v kolech.
     * @param typ Typ bonusu (například "zdraví" nebo "sila").
     */
    public Bonus(String nazev, int bonus, int doba, String typ) {
        super(nazev);
        this.bonus = bonus;
        this.doba = doba;
        this.typ = typ;
    }

    /**
     * Použití bonusového předmětu. Tato metoda přidá hráči odpovídající bonus (zdraví nebo sílu)
     * na určitou dobu a odstraní tento předmět z inventáře.
     *
     * @param hra Instance hry, která obsahuje hráče a inventář.
     * @return Zpráva o tom, jaký bonus byl přidán hráči a jaký je jeho aktuální stav.
     */
    @Override
    public String pouzij(Hra hra) {
        Hrac hrac = hra.getHrac();
        Inventar inventar = hra.getInventar();
        if (typ.equalsIgnoreCase("zdravi")) {
            hrac.pridatZdraviBonus(bonus, doba);  // Přidání bonusu na zdraví
            inventar.vymazPredmet(this);  // Odstranění předmětu z inventáře
            return "Pridal sis bonus " + bonus + " hp na " + doba + " kol a nyni mas " + hrac.getZdravi();
        } else if (typ.equalsIgnoreCase("sila")) {
            hrac.pridatSilaBonus(bonus, doba);  // Přidání bonusu na sílu
            inventar.vymazPredmet(this);  // Odstranění předmětu z inventáře
            return "Pridal sis bonus " + bonus + " sily na " + doba + " kol a nyni mas " + hrac.getSila();
        } else {
            return "Nic";  // Pokud typ není rozpoznán, vrací "Nic"
        }
    }
}
