package prikazy;

import hra.Hra;
import hra.Hrac;
import svet.Mistnost;
import svet.npcs.NPC;

/**
 * Příkaz pro interakci s NPC v aktuální místnosti.
 * Tento příkaz umožňuje hráči mluvit s NPC, pokud je přítomno v místnosti.
 */
public class Mluv implements Command {

    /**
     * Konstruktor pro vytvoření příkazu "Mluv".
     * Tento konstruktor neprovádí žádné konkrétní akce.
     */
    public Mluv() {
    }

    /**
     * Provede akci mluvení s NPC v aktuální místnosti.
     * Pokud je v místnosti NPC, hráč se může rozhodnout, zda s ním chce mluvit.
     * Pokud není žádné NPC, nebo pokud hráč zadaný argument neodpovídá jménu NPC, akce se neprovede.
     *
     * @param argument Jméno NPC, se kterým chce hráč mluvit. Pokud je argument null, bude hráči nabídnuto, aby vybral NPC.
     * @param hra Instance {@link Hra}, která obsahuje informace o aktuálním stavu hry.
     * @return Textová zpráva, která obsahuje výsledek akce (např. zda je NPC přítomno a s jakým NPC hráč může mluvit).
     */
    @Override
    public String execute(String argument, Hra hra) {
        Hrac hrac = hra.getHrac();

        // Pokud není v místnosti žádné NPC, vracíme zprávu
        boolean b = hrac.getAktualniMistnost().getNpc() == null;
        if (b) {
            return "Není tu žádný npc.";
        }

        // Pokud argument je null, informujeme hráče, že musí zadat jméno NPC
        if (argument == null) {
            return "Zadej s kym chces mluvit: " + hrac.getAktualniMistnost().getNpc().getJmeno();
        }

        // Získáme NPC v aktuální místnosti
        Mistnost mistnost = hrac.getAktualniMistnost();
        NPC npc = mistnost.getNpc();

        // Pokud jméno NPC odpovídá argumentu, provede se komunikace
        if (npc != null && npc.getJmeno().equalsIgnoreCase(argument)) {
            return npc.mluv(hra);
        } else {
            return "Nikdo takový tu není.";
        }
    }
}
