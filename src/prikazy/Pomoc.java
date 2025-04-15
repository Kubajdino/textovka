package prikazy;

import hra.Hra;
import hra.SeznamPrikazu;

/**
 * Příkaz, který poskytuje hráči seznam dostupných příkazů a jejich popis.
 * Tento příkaz slouží k zobrazení nápovědy a informací o všech dostupných příkazech ve hře.
 */
public class Pomoc implements Command {

    /**
     * Konstruktor pro vytvoření příkazu "Pomoc".
     * Tento konstruktor neprovádí žádné konkrétní akce.
     */
    public Pomoc(){}

    /**
     * Provede akci zobrazení seznamu dostupných příkazů ve hře.
     * Tento příkaz vrátí seznam všech příkazů, které hráč může použít.
     *
     * @param argument Tento argument není použit v této konkrétní implementaci příkazu.
     * @param hra Instance {@link Hra}, která obsahuje informace o aktuálním stavu hry.
     * @return Textový seznam všech příkazů, které hráč může použít ve hře.
     */
    @Override
    public String execute(String argument, Hra hra) {
        SeznamPrikazu seznamPrikazu = hra.getPrikazy();  // Získání seznamu příkazů z objektu hry
        return seznamPrikazu.vypisSeznamPrikazu(); // Vrácení seznamu příkazů
    }
}
