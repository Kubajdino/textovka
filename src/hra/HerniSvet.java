package hra;

import svet.Mistnost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HerniSvet {
    private Map<String, Mistnost> mistnosti;
    String[] informace;
    private String jmenoHry;

    public HerniSvet() {
        mistnosti = utils.NacitacSouboru.nactiMistnostiZeSouboru("files/mapa.json");
        utils.NacitacSouboru.nactiNPC(mistnosti,"files/npc.json");
        informace = utils.NacitacSouboru.nactiObecneInfo("files/obecneInfo.txt");
        jmenoHry = informace[0];
    }


    public Mistnost getMistnost(String nazev) {
        return mistnosti.get(nazev);
    }


    public String getJmenoHry() {
        return jmenoHry;
    }
}
