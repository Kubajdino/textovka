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
            return hrac.presunSe(argument, svet);
        } else {
            return "Zadej kam chceš jít \n" + hrac.getAktualniMistnost().getSousedi();
        }
    }
}
