package prikazy;

import hra.Hra;

/**
 * Příkaz pro ukončení hry.
 * Tento příkaz způsobí, že hra skončí a program se zavře.
 */
public class Konec implements Command {

    /**
     * Konstruktor pro vytvoření příkazu "Konec".
     * Tento konstruktor neprovádí žádné konkrétní akce.
     */
    public Konec() {
    }

    /**
     * Ukončí hru a ukončí běh programu.
     * Tento příkaz vypíše zprávu o ukončení hry a následně ukončí běh aplikace.
     *
     * @param argument Argument není použitý pro tento příkaz, může být null.
     * @param hra Instance {@link Hra}, která obsahuje informace o aktuálním stavu hry.
     * @return Tento příkaz nevrací žádný výstup, protože okamžitě ukončí běh programu.
     */
    @Override
    public String execute(String argument, Hra hra) {
        System.out.println("Hra ukončena.");
        System.exit(0);  // Ukončení programu
        return null;
    }
}
