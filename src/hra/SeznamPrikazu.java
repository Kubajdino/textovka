package hra;

import prikazy.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeznamPrikazu {
    HashMap<String, Command> sezanmPrikazu = new HashMap<>();
    List<String> tvaryZadani = new ArrayList<>();


    public void addCommand(String tvarZadani,String prikaz, Command command){
        sezanmPrikazu.put(prikaz, command);
        tvaryZadani.add(tvarZadani);
    }

    public String vypisSeznamPrikazu(){
        StringBuilder sb = new StringBuilder();
        for (String prikaz : tvaryZadani) {
            sb.append("\t").append(prikaz).append("\n");
        }
        return sb.toString();
    }

    public HashMap<String, Command> getSezanmPrikazu() {
        return sezanmPrikazu;
    }

}
