package hra;

import svet.Mistnost;
import svet.predmety.Predmet;

public class Hrac {
    private Mistnost aktualniMistnost;
    private int zdravi;
    private int presnost;       // %
    private int kritikal;       // %
    private int sila;
    private boolean zbran;
    private int silaBonus = 0;
    private int silaBonusDoba = 0;
    private int zdraviBonus = 0;
    private int zdraviBonusDoba = 0;

    public Hrac(Mistnost startovniMistnost) {

        this.aktualniMistnost = startovniMistnost;
        this.zdravi = 100;
        this.sila = 10;
        this.presnost = 80;
        this.kritikal = 40;
        this.zbran = false;
    }

    public String presunSe(String nazevMistnosti, HerniSvet svet) {
        if (aktualniMistnost.getSousedi().contains(nazevMistnosti)) {
            aktualniMistnost = svet.getMistnost(nazevMistnosti);
            return  "Přesunul ses do: " + aktualniMistnost.getNazev() + vypisObsahMistnosti();
        } else {
            return "Tam nemůžeš jít.";
        }
    }
    public String vypisObsahMistnosti() {
        Mistnost mistnost = aktualniMistnost;
        String odpoved = "";
        odpoved += "\n"+ mistnost.getPopis();
        if (mistnost.getPredmet() != null) {
            odpoved += "\nVidíš zde předmět: " + mistnost.getPredmet();

        }
        if (mistnost.getNpc() != null) {
            odpoved += "\nJe zde postava: " + mistnost.getNpc().getJmeno();
        }
        return odpoved;
    }


    public Mistnost getAktualniMistnost() {
        return aktualniMistnost;
    }

    public int getZdravi() {
        return zdravi + zdraviBonus;
    }

    public void setZdravi(int zdravi) {
        this.zdravi = zdravi;
    }

    public int getSila() {
        return sila + silaBonus;
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

    public boolean isZbran() {
        return zbran;
    }

    public void setZbran(boolean zbran) {
        this.zbran = zbran;
    }

    public void pridatSilaBonus(int bonus, int doba) {
        this.silaBonus = bonus;
        this.silaBonusDoba = doba;
    }
    public void pridatZdraviBonus(int bonus, int doba) {
        this.zdraviBonus = bonus;
        this.zdraviBonusDoba = doba;
    }
    
    public void snizitSilaBonusDoba() {
        if (silaBonusDoba > 0) {
            silaBonusDoba--;
            if (silaBonusDoba == 0) {
                silaBonus = 0; // Bonus vyprší
            }
        }
    }

    public void snizitZdraviBonusDoba() {
        if (zdraviBonusDoba > 0) {
            zdraviBonusDoba--;
            if (zdraviBonusDoba == 0) {
                zdraviBonus = 0; // Bonus vyprší
            }
        }
    }
}
