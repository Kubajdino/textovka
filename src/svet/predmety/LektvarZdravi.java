package svet.predmety;

import hra.Hra;
import hra.Hrac;
import hra.Inventar;

/**
 * Třída představující lektvar, který léčí zdraví hráče.
 */
public class LektvarZdravi extends Predmet {

    private int zdravi;  // Množství zdraví, které lektvar obnoví

    /**
     * Konstruktor pro vytvoření lektvaru zdraví.
     *
     * @param nazev Název lektvaru.
     * @param zdravi Množství zdraví, které lektvar obnoví.
     */
    public LektvarZdravi(String nazev, int zdravi) {
        super(nazev);
        this.zdravi = zdravi;
    }

    /**
     * Použití lektvaru na hráče. Tato metoda obnoví zdraví hráče, ale ne přes 100.
     * Pokud má hráč již maximální zdraví, neobnoví se žádné zdraví.
     *
     * @param hra Instance hry, která obsahuje hráče a jeho stav.
     * @return Zpráva o tom, kolik zdraví bylo obnoveno a jaké je aktuální zdraví hráče.
     */
    @Override
    public String pouzij(Hra hra) {
        Hrac hrac = hra.getHrac();
        Inventar inventar = hra.getInventar();

        // Obnoví zdraví hráče, ale ne více než 100
        hrac.setZdravi(Math.min(hrac.getZdravi() + zdravi, 100));

        // Odebere lektvar ze seznamu předmětů
        inventar.vymazPredmet(this);

        return "Vylecil ses o " + zdravi + " hp a nyni mas " + hrac.getZdravi();
    }
}
