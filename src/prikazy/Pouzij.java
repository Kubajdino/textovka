package prikazy;

import hra.Hra;
import hra.Inventar;

/**
 * Příkaz, který umožňuje hráči použít předmět z jeho inventáře.
 * Tento příkaz se používá, když hráč chce použít konkrétní předmět, který má v inventáři.
 */
public class Pouzij implements Command {

    /**
     * Konstruktor pro vytvoření příkazu "Použij".
     * Tento konstruktor neprovádí žádné konkrétní akce.
     */
    public Pouzij() {
    }

    /**
     * Provede akci použití předmětu z inventáře.
     * Hráč zadá název předmětu, který chce použít, a pokud je tento předmět v jeho inventáři,
     * bude použit a provede odpovídající akci.
     *
     * @param argument Název předmětu, který chce hráč použít. Tento argument je porovnáván s názvy
     *                 předmětů v inventáři.
     * @param hra Instance {@link Hra}, která obsahuje informace o aktuálním stavu hry.
     * @return Textová zpráva o výsledku použití předmětu, nebo informace o neexistujícím předmětu
     *         v inventáři.
     */
    @Override
    public String execute(String argument, Hra hra) {
        Inventar inventar = hra.getInventar();

        if(inventar.jePrazdny()) {
            return "Není co použít.";  // Inventář je prázdný, není co použít.
        }
        if (argument == null) {
            return "Zadej co chceš použít\n" + inventar.vypis();  // Pokud není argument, hráč je požádán o zadání předmětu.
        }
        if(inventar.najdipredmet(argument) != null){
            return inventar.najdipredmet(argument).pouzij(hra);  // Použití předmětu z inventáře.
        }else{
            return "Nejde použít";  // Pokud předmět není nalezen v inventáři.
        }
    }
}
