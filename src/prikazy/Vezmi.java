package prikazy;

import hra.Hra;
import hra.Hrac;
import hra.Inventar;
import svet.predmety.Predmet;

public class Vezmi implements Command {

    public Vezmi() {
    }

    @Override
    public String execute(String argument, Hra hra) {
        Hrac hrac = hra.getHrac();
        Inventar inventar = hra.getInventar();
        if(hrac.getAktualniMistnost().getPredmety() != null) {
            for(Predmet predmet : hrac.getAktualniMistnost().getPredmety()) {
                if(predmet.getNazev().equalsIgnoreCase(argument)) {
                    inventar.pridejPredmet(predmet);
                    hrac.getAktualniMistnost().getPredmety().remove(predmet);
                    return "Sebral jsi predmet " + predmet;
                }
            }

        }
        return "Predmet tu neni";
    }
}
