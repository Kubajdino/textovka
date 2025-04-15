package prikazy;

import hra.Hra;
import hra.SeznamPrikazu;

public class Pomoc implements Command{

    public Pomoc(){}


    @Override
    public String execute(String argument, Hra hra) {
        SeznamPrikazu seznamPrikazu = hra.getPrikazy();                                                                               //mohl bych pridat vic druhu napoved
        return seznamPrikazu.vypisSeznamPrikazu();
    }
}
