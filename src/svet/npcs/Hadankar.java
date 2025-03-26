package svet.npcs;

import hra.Hra;
import hra.Inventar;
import svet.predmety.Lektvar;
import svet.predmety.Predmet;

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
            inventar.pridejPredmet(new Lektvar("Lektvar zdravi",50));
            return "Získal jsi amulet!";                                               //neni hotovo
        } else {
            return "Hadankář se směje a nic ti nedá...";
        }
    }
}
