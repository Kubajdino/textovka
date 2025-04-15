package svet.npcs;

import hra.Hra;
import hra.Inventar;
import svet.predmety.LektvarZdravi;

import java.util.Scanner;

/**
 * Třída představuje postavu Hadankáře (NPC), která má za úkol položit hráči hádanku.
 * Pokud hráč odpoví správně, může získat předmět. Pokud odpoví špatně, nebude nic odměněn.
 */
public class Hadankar extends NPC {
    private String hadanka;  // Hádanka, kterou Hadankář položil hráči.
    private String odpoved;  // Odpověď na hádanku.

    /**
     * Konstruktor pro vytvoření Hadankáře s názvem, dialogem, hádankou a odpovědí.
     *
     * @param jmeno Název Hadankáře.
     * @param dialog Dialog, který Hadankář říká hráči, když s ním interaguje.
     * @param hadanka Hádanka, kterou Hadankář hráči položí.
     * @param odpoved Odpověď na hádanku.
     */
    public Hadankar(String jmeno, String dialog, String hadanka, String odpoved) {
        super(jmeno, dialog);
        this.hadanka = hadanka;
        this.odpoved = odpoved;
    }

    /**
     * Vrací hádanku, kterou Hadankář položil hráči.
     *
     * @return Hádanka.
     */
    public String getHadanka() {
        return hadanka;
    }

    /**
     * Vrací odpověď na hádanku.
     *
     * @return Odpověď.
     */
    public String getOdpoved() {
        return odpoved;
    }

    /**
     * Metoda, která umožňuje hráči odpovědět na hádanku.
     * Pokud hráč odpoví správně, vrátí hodnotu true. Pokud špatně, vrátí hodnotu false.
     *
     * @return true pokud hráč odpověděl správně, jinak false.
     */
    private boolean hadej() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(hadanka);
        System.out.print("> ");
        String vstup = scanner.nextLine().trim();
        if (vstup.equalsIgnoreCase(odpoved)) {
            System.out.println("Spravne");
            return true;
        } else {
            System.out.println("Spatna odpoved");
            return false;
        }
    }

    /**
     * Metoda, která vykoná interakci s hráčem.
     * Pokud hráč správně odpoví na hádanku, získá předmět (pokud nějaký existuje).
     * Pokud odpoví špatně, nebude odměněn.
     *
     * @param hra Instance hry, která obsahuje aktuální stav hry.
     * @return Text, který popisuje výsledek interakce s Hadankářem.
     */
    @Override
    public String mluv(Hra hra) {
        Inventar inventar = hra.getInventar();
        System.out.println(getDialog());

        if (hadej()) {
            // Pokud je k dispozici předmět, přidá se do inventáře
            if (getPredmet() != null) inventar.pridejPredmet(getPredmet());
            this.setPredmet(null);
            return "Získal jsi tekutinu!";
        } else {
            return "Hadankář se směje a nic ti nedá...";
        }
    }
}
