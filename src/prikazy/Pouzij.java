package prikazy;

import hra.Hra;
import hra.Inventar;

public class Pouzij implements Command {

    public Pouzij() {
    }

    @Override
    public String execute(String argument, Hra hra) {
        Inventar inventar = hra.getInventar();

        if(inventar.jePrazdny()) {
            return "Neni co pouzit.";
        }
        if (argument == null) {
            return "Zadej co chces pouzit\n" + inventar.vypis();
        }
        if(inventar.najdipredmet(argument) != null){
            return inventar.najdipredmet(argument).pouzij(hra);
        }else{
            return "Nejde pouzit";
        }

    }
}
