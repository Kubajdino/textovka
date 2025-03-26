package prikazy;

import hra.Hra;
import hra.Inventar;

public class OtevriInventar implements Command{


    public OtevriInventar() {

    }

    @Override
    public String execute(String argument, Hra hra) {
        Inventar inventar = hra.getInventar();
        if (inventar.jePrazdny()) {
            return "Inventář je prázdný.";
        } else {
            return "Obsah inventáře: " + inventar.vypis();
        }
    }
}
