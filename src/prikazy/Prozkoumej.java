package prikazy;

import hra.Hra;
import hra.Hrac;
import svet.Mistnost;

public class Prozkoumej implements Command{

    public Prozkoumej() {
    }

    @Override
    public String execute(String argument, Hra hra) {
        Hrac hrac = hra.getHrac();
        Mistnost mistnost = hrac.getAktualniMistnost();

        if (mistnost.getPredmet() != null) {
            return "Vidíš zde předmět: " + mistnost.getPredmet().getNazev();
        }

        if (mistnost.getNpc() != null) {
            return "Je zde postava: " + mistnost.getNpc().getJmeno();
        }

        return "Jsi v místnosti: " + mistnost.getNazev() + mistnost.getPopis();

    }
}
