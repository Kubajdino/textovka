package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import svet.Mistnost;
import svet.npcs.Bojovnik;
import svet.npcs.Hadankar;
import svet.npcs.NPC;
import svet.npcs.Univerzal;
import svet.predmety.Informace;
import svet.predmety.Predmet;
import svet.predmety.Zbran;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NacitacSouboru {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Predmet.class, (JsonDeserializer<Predmet>) (json, typeOfT, context) -> {
                JsonObject jsonObject = json.getAsJsonObject();
                String typ = jsonObject.get("typ").getAsString();

                switch (typ) {
                    case "Zbran":
                        return new Zbran(jsonObject.get("nazev").getAsString(),
                                jsonObject.get("sila").getAsInt());
                    case "Informace":
                        return new Informace(jsonObject.get("nazev").getAsString(),
                                jsonObject.get("informace").getAsString());
                    default:
                        return null;
                }
            }).create();

    public static Map<String, Mistnost> nactiMistnostiZeSouboru(String soubor) {
        Map<String, Mistnost> mistnosti = new HashMap<>();

        try (FileReader reader = new FileReader(soubor)) {
            Type mistnostListType = new TypeToken<List<Mistnost>>() {}.getType();
            List<Mistnost> seznamMistnosti = gson.fromJson(reader, mistnostListType);

            for (Mistnost mistnost : seznamMistnosti) {
                mistnosti.put(mistnost.getNazev(), mistnost);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mistnosti;
    }

    public static void nactiNPC(Map<String, Mistnost> mistnosti, String soubor) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(soubor)) {
            Type npcListType = new TypeToken<List<JsonNPC>>() {
            }.getType();
            List<JsonNPC> npcList = gson.fromJson(reader, npcListType);

            for (JsonNPC jsonNPC : npcList) {
                NPC npc;
                if (jsonNPC.typ.equals("hadankar")) {
                    npc = new Hadankar(jsonNPC.jmeno, jsonNPC.dialog, jsonNPC.hadanka, jsonNPC.odpoved);
                } else if (jsonNPC.typ.equals("bojovnik")) {
                    npc = new Bojovnik(jsonNPC.jmeno, jsonNPC.dialog, jsonNPC.sila, jsonNPC.zdravi, jsonNPC.kritikal);
                } else {
                    npc = new Univerzal(jsonNPC.jmeno, jsonNPC.dialog);
                }

                // Najdeme správnou místnost a přiřadíme do ní NPC
                Mistnost mistnost = mistnosti.get(jsonNPC.mistnost);
                if (mistnost != null) {
                    mistnost.setNpc(npc);
                } else {
                    System.out.println("Chyba: Místnost '" + jsonNPC.mistnost + "' neexistuje!");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Pomocná třída pro načítání JSON dat
    private static class JsonNPC {
        String jmeno;
        String dialog;
        String typ;
        String mistnost;
        String hadanka;
        String odpoved;
        int zdravi;
        int sila;
        int kritikal;
    }

   public static String[] nactiObecneInfo(String soubor){
       String[] informace = new String[2];

        try (
       BufferedReader br = new BufferedReader(new FileReader(soubor))) {
           String line;

           while ((line = br.readLine()) != null) {
               String casti[] = line.split(";");
               informace[0] = casti[0];
               informace[1] = casti[1];
           }
       } catch (IOException e) {
           e.printStackTrace();  // Chyba při čtení souboru
       }
        return informace;
   }
}



