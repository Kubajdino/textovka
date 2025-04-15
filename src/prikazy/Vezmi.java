package prikazy;

import hra.Hra;
import hra.Hrac;
import hra.Inventar;
import svet.Mistnost;
import svet.npcs.NPC;
import svet.predmety.Predmet;

public class Vezmi implements Command {

    public Vezmi() {
    }

    @Override
    public String execute(String argument, Hra hra) {
        Hrac hrac = hra.getHrac();
        Inventar inventar = hra.getInventar();

        if (argument == null) {
            if(hrac.getAktualniMistnost().getPredmet() == null){
            return "Neni co vzit";
            }else{
                return "Zadej co chces vzit: "+hrac.getAktualniMistnost().getPredmet();
            }

        }
        if(hrac.getAktualniMistnost().getPredmet() != null) {
            if(hrac.getAktualniMistnost().getPredmet().getNazev().equalsIgnoreCase(argument)) {
                    inventar.pridejPredmet(hrac.getAktualniMistnost().getPredmet());
                    hrac.getAktualniMistnost().setPredmet(null);
                return "Sebral jsi predmet ";
            }else{
                return "Toto nejde sebrat";
            }
        }else{
            return "Neni co vzit";
        }

    }
}
