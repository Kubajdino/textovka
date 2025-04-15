package svet.npcs;

import hra.Hra;
import svet.predmety.Predmet;

/**
 * Třída představuje bojovníka (NPC), který má specifické atributy jako sílu, zdraví a kritickou šanci.
 * Bojovník je typ NPC (nehratelné postavy), který může mít interakce s hráčem, například dialogy,
 * a může být součástí bojového systému.
 */
public class Bojovnik extends NPC {

    private int sila;       // Síla bojovníka, která ovlivňuje jeho útoky.
    private int zdravi;     // Zdraví bojovníka, které určuje, kolik zranění může přežít.
    private int kritikal;   // Kritická šance bojovníka, která ovlivňuje pravděpodobnost kritického zásahu.

    /**
     * Konstruktor pro vytvoření bojovníka s názvem, dialogem, silou, zdravím a kritickou šancí.
     *
     * @param jmeno Název bojovníka.
     * @param dialog Dialog, který bojovník říká hráči, když s ním interaguje.
     * @param sila Síla bojovníka, která ovlivňuje jeho schopnost útočit.
     * @param zdravi Zdraví bojovníka, které určuje, jak dlouho vydrží v boji.
     * @param kritikal Kritická šance bojovníka, která ovlivňuje pravděpodobnost kritického zásahu.
     */
    public Bojovnik(String jmeno, String dialog, int sila, int zdravi, int kritikal) {
        super(jmeno, dialog);
        this.sila = sila;
        this.zdravi = zdravi;
        this.kritikal = kritikal;
    }

    /**
     * Vrací sílu bojovníka.
     *
     * @return Síla bojovníka.
     */
    public int getSila() {
        return sila;
    }

    /**
     * Nastavuje sílu bojovníka.
     *
     * @param sila Nová hodnota síly bojovníka.
     */
    public void setSila(int sila) {
        this.sila = sila;
    }

    /**
     * Vrací zdraví bojovníka.
     *
     * @return Zdraví bojovníka.
     */
    public int getZdravi() {
        return zdravi;
    }

    /**
     * Vrací kritickou šanci bojovníka.
     *
     * @return Kritická šance bojovníka.
     */
    public int getKritikal() {
        return kritikal;
    }

    /**
     * Nastavuje zdraví bojovníka.
     *
     * @param zdravi Nová hodnota zdraví bojovníka.
     */
    public void setZdravi(int zdravi) {
        this.zdravi = zdravi;
    }

    /**
     * Vrací dialog, který bojovník říká hráči.
     *
     * @param hra Instance hry, která obsahuje aktuální stav hry.
     * @return Dialog bojovníka.
     */
    @Override
    public String mluv(Hra hra) {
        return getDialog();
    }
}
