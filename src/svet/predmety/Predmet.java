package svet.predmety;

import hra.Hra;

/**
 * Abstraktní třída představující předmět, který může hráč použít v hře.
 * Každý konkrétní typ předmětu musí implementovat metodu {@link #pouzij(Hra)}.
 */
public abstract class Predmet {

    private String nazev;  // Název předmětu

    /**
     * Konstruktor pro vytvoření předmětu s názvem.
     *
     * @param nazev Název předmětu.
     */
    public Predmet(String nazev) {
        this.nazev = nazev;
    }

    /**
     * Abstraktní metoda, která musí být implementována každým konkrétním předmětem.
     * Tato metoda popisuje chování předmětu, když je použit v rámci hry.
     *
     * @param hra Instance hry, která obsahuje stav hry, hráče a jeho inventář.
     * @return Zpráva o výsledku použití předmětu.
     */
    public abstract String pouzij(Hra hra);

    /**
     * Getter pro název předmětu.
     *
     * @return Název předmětu.
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Setter pro název předmětu.
     *
     * @param nazev Nový název předmětu.
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    /**
     * Vrací název předmětu jako řetězec.
     *
     * @return Název předmětu.
     */
    @Override
    public String toString() {
        return nazev;
    }
}
