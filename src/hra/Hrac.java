package hra;

import svet.Mistnost;
import svet.predmety.Predmet;

/**
 * Třída {@code Hrac} reprezentuje hráče ve hře.
 * Uchovává informace o aktuální místnosti, statistikách a dočasných bonusech.
 */
public class Hrac {
    /** Místnost, ve které se hráč aktuálně nachází. */
    private Mistnost aktualniMistnost;

    /** Základní zdraví hráče. */
    private int zdravi;

    /** Šance na zásah v procentech. */
    private int presnost;

    /** Šance na kritický zásah v procentech. */
    private int kritikal;

    /** Základní síla hráče. */
    private int sila;

    /** Informace, zda hráč drží zbraň. */
    private boolean zbran;

    /** Dočasný bonus k síle. */
    private int silaBonus = 0;

    /** Počet kol, po která platí bonus k síle. */
    private int silaBonusDoba = 0;

    /** Dočasný bonus ke zdraví. */
    private int zdraviBonus = 0;

    /** Počet kol, po která platí bonus ke zdraví. */
    private int zdraviBonusDoba = 0;

    /**
     * Vytvoří nového hráče s výchozími statistikami a umístěním.
     *
     * @param startovniMistnost místnost, kde hráč začne hru
     */
    public Hrac(Mistnost startovniMistnost) {
        this.aktualniMistnost = startovniMistnost;
        this.zdravi = 100;
        this.sila = 10;
        this.presnost = 80;
        this.kritikal = 40;
        this.zbran = false;
    }

    /**
     * Pokusí se přesunout hráče do jiné místnosti, pokud je dostupná.
     *
     * @param nazevMistnosti cílová místnost
     * @param svet           aktuální herní svět
     * @return textová odpověď hráči
     */
    public String presunSe(String nazevMistnosti, HerniSvet svet) {
        if (aktualniMistnost.getSousedi().contains(nazevMistnosti)) {
            aktualniMistnost = svet.getMistnost(nazevMistnosti);
            return "Přesunul ses do: " + aktualniMistnost.getNazev() + vypisObsahMistnosti();
        } else {
            return "Tam nemůžeš jít.";
        }
    }

    /**
     * Vypíše popis aktuální místnosti včetně přítomných předmětů a postav.
     *
     * @return textový výpis obsahu místnosti
     */
    public String vypisObsahMistnosti() {
        Mistnost mistnost = aktualniMistnost;
        String odpoved = "\n" + mistnost.getPopis();
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

    /**
     * Přidá hráči dočasný bonus k síle.
     *
     * @param bonus výše bonusu
     * @param doba  počet tahů, po které bonus platí
     */
    public void pridatSilaBonus(int bonus, int doba) {
        this.silaBonus = bonus;
        this.silaBonusDoba = doba;
    }

    /**
     * Přidá hráči dočasný bonus ke zdraví.
     *
     * @param bonus výše bonusu
     * @param doba  počet tahů, po které bonus platí
     */
    public void pridatZdraviBonus(int bonus, int doba) {
        this.zdraviBonus = bonus;
        this.zdraviBonusDoba = doba;
    }

    /**
     * Snižuje dobu trvání bonusu k síle. Po vypršení bonus zmizí.
     */
    public void snizitSilaBonusDoba() {
        if (silaBonusDoba > 0) {
            silaBonusDoba--;
            if (silaBonusDoba == 0) {
                silaBonus = 0;
            }
        }
    }

    /**
     * Snižuje dobu trvání bonusu ke zdraví. Po vypršení bonus zmizí.
     */
    public void snizitZdraviBonusDoba() {
        if (zdraviBonusDoba > 0) {
            zdraviBonusDoba--;
            if (zdraviBonusDoba == 0) {
                zdraviBonus = 0;
            }
        }
    }
}
