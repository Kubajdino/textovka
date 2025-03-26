package svet;

import svet.npcs.NPC;

import java.util.List;

public class Mistnost {
    private String nazev;
    private String popis;
    private List<String> sousedi;
    private NPC npc;
    private Predmet predmet;

    public Mistnost(String nazev, String popis, List<String> sousedi) {
        this.nazev = nazev;
        this.popis = popis;
        this.sousedi = sousedi;
    }

    public Mistnost(String nazev, String popis, List<String> sousedi, NPC npc, Predmet predmet) {
        this.nazev = nazev;
        this.popis = popis;
        this.sousedi = sousedi;
        this.npc = npc;
        this.predmet = predmet;
    }

    public List<String> getSousedi() {
        return sousedi;
    }

    public String getNazev() {
        return nazev;
    }

    public NPC getNpc() {
        return npc;
    }
    public void setNpc(NPC npc) {
        this.npc = npc;
    }


    public String getPopis() {
        return popis;
    }

    public Predmet getPredmet() {
        return predmet;
    }

}
