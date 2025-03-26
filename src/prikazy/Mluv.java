package prikazy;

import hra.Hra;
import hra.Hrac;
import hra.Inventar;
import svet.Mistnost;
import svet.Predmet;
import svet.npcs.Hadankar;
import svet.npcs.NPC;

import java.awt.*;

public class Mluv implements Command{
    public Mluv() {
    }

    @Override
    public String execute(String argument, Hra hra) {
        Hrac hrac = hra.getHrac();
        if (argument == null) {
            return "S kým mám mluvit?\n" + hrac.getAktualniMistnost().getNpc().getJmeno();

        }

        Mistnost mistnost = hrac.getAktualniMistnost();
        NPC npc = mistnost.getNpc();

        if (npc != null && npc.getJmeno().equalsIgnoreCase(argument)) {
            return npc.mluv(hra);
        } else {
            return "Nikdo takový tu není.";
        }

    }
}
