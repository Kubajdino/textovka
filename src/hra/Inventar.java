package hra;

import svet.predmety.Predmet;

import java.util.ArrayList;
import java.util.List;

public class Inventar {

    private List<Predmet> inventar;

    public Inventar() {
        this.inventar = new ArrayList<Predmet>();
    }

    public void pridejPredmet(Predmet predmet) {
        if (inventar.size()<6) {
            inventar.add(predmet);
        }else System.out.println("Inventar je plny");
    }

    public void vymazPredmet(Predmet predmet) {
        if(inventar.contains(predmet)){
             inventar.remove(predmet);
        } else System.out.println("Tento predmet neni v inventari");
    }

    public String vypis(){
        StringBuilder sb = new StringBuilder();
        for(Predmet p: inventar){
            sb.append(p.getNazev()).append(", ");
        }
        return sb.toString();
    }

    public List<Predmet> getInventar() {
        return inventar;
    }

    public boolean jePrazdny() {
        return inventar.isEmpty();
    }
}

