package hra;

import svet.predmety.LektvarZdravi;
import svet.predmety.Predmet;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída {@code Inventar} reprezentuje inventář hráče, kde si může uchovávat předměty.
 * Má maximální kapacitu 6 předmětů.
 */
public class Inventar {

    /** Seznam předmětů v inventáři. */
    private List<Predmet> inventar;

    /**
     * Vytvoří nový prázdný inventář.
     */
    public Inventar() {
        this.inventar = new ArrayList<Predmet>();
    }

    /**
     * Přidá předmět do inventáře, pokud není plný (max 6 předmětů).
     *
     * @param predmet předmět k přidání
     */
    public void pridejPredmet(Predmet predmet) {
        if (inventar.size() < 6) {
            inventar.add(predmet);
        } else {
            System.out.println("Inventar je plny");
        }
    }

    /**
     * Odstraní předmět z inventáře, pokud se v něm nachází.
     *
     * @param predmet předmět k odebrání
     */
    public void vymazPredmet(Predmet predmet) {
        if (inventar.contains(predmet)) {
            inventar.remove(predmet);
        } else {
            System.out.println("Tento predmet neni v inventari");
        }
    }

    /**
     * Vrátí textový výpis všech předmětů v inventáři.
     *
     * @return seznam názvů předmětů oddělený čárkami
     */
    public String vypis() {
        StringBuilder sb = new StringBuilder();
        for (Predmet p : inventar) {
            sb.append(p.getNazev()).append(", ");
        }
        return sb.toString();
    }

    /**
     * Vyhledá předmět podle názvu (nerozlišuje velikost písmen).
     *
     * @param nazev název hledaného předmětu
     * @return nalezený předmět nebo {@code null}, pokud nebyl nalezen
     */
    public Predmet najdipredmet(String nazev) {
        for (Predmet p : inventar) {
            if (p.getNazev().equalsIgnoreCase(nazev)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Vrátí seznam všech předmětů v inventáři.
     *
     * @return seznam předmětů
     */
    public List<Predmet> getInventar() {
        return inventar;
    }

    /**
     * Zjistí, zda je inventář prázdný.
     *
     * @return {@code true}, pokud je inventář prázdný, jinak {@code false}
     */
    public boolean jePrazdny() {
        return inventar.isEmpty();
    }
}
