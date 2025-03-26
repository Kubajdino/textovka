package prikazy;

import hra.HerniSvet;
import hra.Hra;
import hra.Hrac;

public class Jdi implements Command {


    public Jdi() {
    }

    @Override
    public String execute(String argument, Hra hra) {
        Hrac hrac = hra.getHrac();
        HerniSvet svet = hra.getSvet();
        if (argument != null) {
            hrac.presunSe(argument, svet);
            return "Presnunul se do " + hrac.getAktualniMistnost().getNazev();
        } else {
            return "Kam chceš jít?\n" + hrac.getAktualniMistnost().getSousedi();
        }
    }
}
