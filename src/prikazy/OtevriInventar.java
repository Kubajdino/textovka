package prikazy;

import hra.Hra;
import hra.Inventar;

/**
 * Příkaz pro otevření inventáře hráče a zobrazení jeho obsahu.
 * Tento příkaz zkontroluje, zda má hráč nějaké předměty v inventáři, a pokud ano, vypíše jejich seznam.
 */
public class OtevriInventar implements Command {

    /**
     * Konstruktor pro vytvoření příkazu "OtevriInventar".
     * Tento konstruktor neprovádí žádné konkrétní akce.
     */
    public OtevriInventar() {

    }

    /**
     * Provede akci otevření inventáře a zobrazení jeho obsahu.
     * Pokud je inventář prázdný, hráč dostane zprávu, že není nic k zobrazení.
     * Pokud obsahuje předměty, zobrazí jejich seznam.
     *
     * @param argument Tento argument není použit v této konkrétní implementaci příkazu.
     * @param hra Instance {@link Hra}, která obsahuje informace o aktuálním stavu hry.
     * @return Textová zpráva obsahující buď seznam předmětů v inventáři, nebo informaci o tom, že inventář je prázdný.
     */
    @Override
    public String execute(String argument, Hra hra) {
        Inventar inventar = hra.getInventar();

        // Pokud je inventář prázdný, vrátí zprávu, že není co zobrazit
        if (inventar.jePrazdny()) {
            return "Inventář je prázdný.";
        } else {
            // Pokud obsahuje předměty, vrátí seznam předmětů
            return "Obsah inventáře: " + inventar.vypis();
        }
    }
}
