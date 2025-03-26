package hra;

import svet.Mistnost;
import svet.predmety.Predmet;

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
            return  "Přesunul ses do: " + aktualniMistnost.getNazev() + vypisObsahMistnosti();
        } else {
            return "Tam nemůžeš jít.";
        }
    }
    private String vypisObsahMistnosti() {
        Mistnost mistnost = aktualniMistnost;
        String odpoved = "";
        if (mistnost.getPredmety() != null) {
            for (Predmet predmet : mistnost.getPredmety()) {
                odpoved += "\nVidíš zde předmět: " + predmet;
            }
        }
        if (mistnost.getNpc() != null) {
            odpoved += "\nJe zde postava: " + mistnost.getNpc().getJmeno();
        }

        odpoved += "\nJsi v místnosti: " + mistnost.getNazev() +"\n"+ mistnost.getPopis();

        return odpoved;
    }


    public Mistnost getAktualniMistnost() {
        return aktualniMistnost;
    }
}
