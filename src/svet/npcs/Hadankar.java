package svet.npcs;

import hra.Hra;
import hra.Inventar;
import svet.predmety.LektvarZdravi;

import java.util.Scanner;

public class Hadankar extends NPC{
    private String hadanka;
    private String odpoved;


    public Hadankar(String jmeno, String dialog, String hadanka, String odpoved) {
        super(jmeno,dialog);
        this.hadanka = hadanka;
        this.odpoved = odpoved;
    }

    public String getHadanka() {
        return hadanka;
    }

    public String getOdpoved() {
        return odpoved;
    }


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

    @Override
    public String mluv(Hra hra) {
        Inventar inventar = hra.getInventar();
        System.out.println(getDialog());


        if (hadej()) {
            if(getPredmet() != null)inventar.pridejPredmet(getPredmet());
            this.setPredmet(null);
            return "Získal jsi tekutinu!";
        } else {
            return "Hadankář se směje a nic ti nedá...";
        }
    }
}
