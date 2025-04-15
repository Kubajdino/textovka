package prikazy;

import hra.Hra;

/**
 * Rozhraní pro všechny příkazy ve hře.
 * Každý příkaz implementuje metodu {@link #execute(String, Hra)}, která definuje vykonání dané akce.
 */
public interface Command {

    /**
     * Provádí akci daného příkazu.
     * Tato metoda je implementována v každé třídě, která reprezentuje konkrétní příkaz (např. boj, útok, obrana).
     *
     * @param argument Argument, který může být předán příkazu, např. jméno NPC pro boj.
     *                 Může být null, pokud příkaz nevyžaduje argumenty.
     * @param hra Instance {@link Hra}, která obsahuje kontext pro vykonání příkazu.
     *           Obsahuje informace o hráči, světě a dalších herních mechanismech.
     * @return Řetězec popisující výsledek vykonání příkazu, např. textová odpověď.
     */
    public String execute(String argument, Hra hra);
}
