package hra;

import svet.Mistnost;
import svet.Predmet;

import java.util.ArrayList;
import java.util.List;

public class Hrac {
    private Mistnost aktualniMistnost;
    private int zivoty;
    private boolean hledanost;

    public Hrac(Mistnost startovniMistnost) {
        this.aktualniMistnost = startovniMistnost;
    }

    public String presunSe(String nazevMistnosti, HerniSvet svet) {
        if (aktualniMistnost.getSousedi().contains(nazevMistnosti)) {
            aktualniMistnost = svet.getMistnost(nazevMistnosti);
            return "Přesunul ses do: " + aktualniMistnost.getNazev();
        } else {
            return "Tam nemůžeš jít.";
        }
    }


    public Mistnost getAktualniMistnost() {
        return aktualniMistnost;
    }
}
