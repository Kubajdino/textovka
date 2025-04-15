package prikazy;

import hra.Hra;
import hra.Hrac;
import hra.Inventar;
import svet.Mistnost;
import svet.npcs.NPC;
import svet.predmety.Predmet;

/**
 * Příkaz, který umožňuje hráči sebrat předmět z aktuální místnosti a přidat ho do inventáře.
 * Tento příkaz se používá, když hráč chce vzít konkrétní předmět, který se nachází v místnosti,
 * ve které se momentálně nachází.
 */
public class Vezmi implements Command {

    /**
     * Konstruktor pro vytvoření příkazu "Vezmi".
     * Tento konstruktor neprovádí žádné konkrétní akce.
     */
    public Vezmi() {
    }

    /**
     * Provede akci sebrání předmětu z aktuální místnosti.
     * Hráč zadá název předmětu, který chce sebrat, a pokud je tento předmět v místnosti,
     * bude přidán do inventáře hráče.
     *
     * @param argument Název předmětu, který chce hráč sebrat. Tento argument je porovnáván s názvem
     *                 předmětu v aktuální místnosti.
     * @param hra Instance {@link Hra}, která obsahuje informace o aktuálním stavu hry.
     * @return Textová zpráva o výsledku sebrání předmětu, nebo informace o tom, že není co sebrat
     *         nebo že zadaný předmět nelze vzít.
     */
    @Override
    public String execute(String argument, Hra hra) {
        Hrac hrac = hra.getHrac();
        Inventar inventar = hra.getInventar();

        if (argument == null) {
            if (hrac.getAktualniMistnost().getPredmet() == null) {
                return "Není co vzít";  // V místnosti není žádný předmět k sebrání.
            } else {
                return "Zadej, co chceš vzít: " + hrac.getAktualniMistnost().getPredmet();
                // Hráč je požádán o zadání názvu předmětu, který chce sebrat.
            }
        }

        // Kontrola, zda je v místnosti nějaký předmět.
        if (hrac.getAktualniMistnost().getPredmet() != null) {
            if (hrac.getAktualniMistnost().getPredmet().getNazev().equalsIgnoreCase(argument)) {
                // Pokud název předmětu odpovídá argumentu, přidej ho do inventáře.
                inventar.pridejPredmet(hrac.getAktualniMistnost().getPredmet());
                hrac.getAktualniMistnost().setPredmet(null);
                return "Sebral jsi předmět " + argument;  // Potvrzení o sebrání předmětu.
            } else {
                return "Toto nejde sebrat";  // Pokud je zadaný předmět v místnosti, ale neodpovídá názvu.
            }
        } else {
            return "Není co vzít";  // Pokud v místnosti není žádný předmět k sebrání.
        }
    }
}
