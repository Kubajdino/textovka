package hra;

import svet.Mistnost;
import svet.predmety.Predmet;

public class Hrac {
    private Mistnost aktualniMistnost;
    private int zdravi;
    private int presnost;       // %
    private int kritikal;       // %
    private int sila;
    private boolean hledanost;

    public Hrac(Mistnost startovniMistnost) {

        this.aktualniMistnost = startovniMistnost;
        this.zdravi = 20;                                               // pak zmen na 100
        this.sila = 10;
        this.presnost = 100;
        this.kritikal = 50;
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

        odpoved += "\n"+ mistnost.getPopis();

        return odpoved;
    }


    public Mistnost getAktualniMistnost() {
        return aktualniMistnost;
    }

    public int getZdravi() {
        return zdravi;
    }

    public void setZdravi(int zdravi) {
        this.zdravi = zdravi;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public int getPresnost() {
        return presnost;
    }

    public void setPresnost(int presnost) {
        this.presnost = presnost;
    }

    public int getKritikal() {
        return kritikal;
    }
}
