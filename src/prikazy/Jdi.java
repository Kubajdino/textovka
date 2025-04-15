package prikazy;

import hra.HerniSvet;
import hra.Hra;
import hra.Hrac;

/**
 * Příkaz pro přesun hráče do jiné místnosti.
 * Tento příkaz umožňuje hráči pohybovat se mezi místnostmi ve hře.
 * Pokud není zadán žádný argument, příkaz vypíše dostupné sousedy (místnosti, do kterých může hráč jít).
 */
public class Jdi implements Command {

    /**
     * Konstruktor pro vytvoření příkazu "Jdi".
     */
    public Jdi() {
    }

    /**
     * Provádí přesun hráče do jiné místnosti na základě zadaného argumentu.
     * Pokud argument není zadán, vypíše seznam dostupných sousedních místností.
     *
     * @param argument Argument, který určuje název místnosti, do které se hráč chce přesunout.
     *                 Pokud je null, příkaz vypíše seznam sousedních místností.
     * @param hra Instance {@link Hra}, která obsahuje informace o hráči a herním světě.
     * @return Řetězec s informací o výsledku pokusu o přesun nebo seznam dostupných sousedních místností.
     */
    @Override
    public String execute(String argument, Hra hra) {
        Hrac hrac = hra.getHrac();
        HerniSvet svet = hra.getSvet();
        if (argument != null) {
            return hrac.presunSe(argument, svet);
        } else {
            return "Zadej kam chceš jít \n" + hrac.getAktualniMistnost().getSousedi();
        }
    }
}
