package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import svet.Mistnost;
import svet.npcs.*;
import svet.predmety.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Třída pro načítání herních dat ze souborů ve formátu JSON.
 * Obsahuje metody pro načítání informací o místnostech, NPC a předmětech do herního světa.
 */
public class NacitacSouboru {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Predmet.class, (JsonDeserializer<Predmet>) (json, typeOfT, context) -> {
                JsonObject jsonObject = json.getAsJsonObject();
                String typ = jsonObject.get("typ").getAsString();

                // Deserializace různých typů předmětů
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

    /**
     * Načítá místnosti ze souboru a vrací je jako mapu, kde klíč je název místnosti.
     *
     * @param soubor Název souboru, ze kterého se načítají místnosti.
     * @return Mapa místností, kde klíč je název místnosti a hodnota je objekt místnosti.
     */
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

    /**
     * Načítá NPC z JSON souboru a přiřazuje je k odpovídajícím místnostem.
     *
     * @param mistnosti Mapa místností, do kterých budou NPC přidána.
     * @param soubor Název souboru s daty o NPC.
     */
    public static void nactiNPC(Map<String, Mistnost> mistnosti, String soubor) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(soubor)) {
            Type npcListType = new TypeToken<List<JsonNPC>>() {}.getType();
            List<JsonNPC> npcList = gson.fromJson(reader, npcListType);

            for (JsonNPC jsonNPC : npcList) {
                NPC npc;
                if (jsonNPC.NPCTyp.equals("hadankar")) {
                    npc = new Hadankar(jsonNPC.jmeno, jsonNPC.dialog, jsonNPC.hadanka, jsonNPC.odpoved);
                } else if (jsonNPC.NPCTyp.equals("bojovnik")) {
                    npc = new Bojovnik(jsonNPC.jmeno, jsonNPC.dialog, jsonNPC.sila, jsonNPC.zdravi, jsonNPC.kritikal);
                } else if (jsonNPC.NPCTyp.equals("spredmetem")) {
                    npc = new SPredmetem(jsonNPC.jmeno, jsonNPC.dialog);
                } else {
                    npc = new Univerzal(jsonNPC.jmeno, jsonNPC.dialog);
                }

                // Přiřazení NPC do správné místnosti
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

    /**
     * Načítá obecné informace ze souboru.
     *
     * @param soubor Název souboru s obecnými informacemi.
     * @return Pole řetězců, kde první prvek je název a druhý prvek je popis.
     */
    public static String[] nactiObecneInfo(String soubor) {
        String[] informace = new String[2];

        try (BufferedReader br = new BufferedReader(new FileReader(soubor))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] casti = line.split(";");
                informace[0] = casti[0];
                informace[1] = casti[1];
            }
        } catch (IOException e) {
            e.printStackTrace();  // Chyba při čtení souboru
        }
        return informace;
    }

    /**
     * Načítá předměty ze souboru a přiřazuje je do místností nebo k NPC.
     *
     * @param mistnosti Mapa místností, do kterých budou předměty přiřazeny.
     * @param soubor Název souboru s daty o předmětech.
     */
    public static void nactiPredmet(Map<String, Mistnost> mistnosti, String soubor) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(soubor)) {
            Type predmetListType = new TypeToken<List<JsonPredmet>>() {}.getType();
            List<JsonPredmet> predmetList = gson.fromJson(reader, predmetListType);

            for (JsonPredmet jsonPredmet : predmetList) {
                Predmet predmet = null;
                if (jsonPredmet.PredmetTyp.equals("informace")) {
                    predmet = new Informace(jsonPredmet.nazev, jsonPredmet.informace);
                } else if (jsonPredmet.PredmetTyp.equals("zbran")) {
                    predmet = new Zbran(jsonPredmet.nazev, jsonPredmet.sila);
                } else if (jsonPredmet.PredmetTyp.equals("lektvatZdravi")) {
                    predmet = new LektvarZdravi(jsonPredmet.nazev, jsonPredmet.zdravi);
                } else if (jsonPredmet.PredmetTyp.equals("bonus")) {
                    predmet = new Bonus(jsonPredmet.nazev, jsonPredmet.bonus, jsonPredmet.doba, jsonPredmet.typ);
                }

                // Přiřazení předmětu do místnosti nebo k NPC
                if (jsonPredmet.npc != 1) {
                    if (jsonPredmet.mistnost != null) {
                        Mistnost mistnost = mistnosti.get(jsonPredmet.mistnost);
                        if (mistnost != null) {
                            mistnost.setPredmet(predmet);
                        } else {
                            System.out.println("Chyba: Místnost '" + jsonPredmet.mistnost + "' neexistuje!");
                        }
                    }
                } else {
                    Mistnost mistnost = mistnosti.get(jsonPredmet.mistnost);
                    NPC npc = mistnost.getNpc();
                    npc.setPredmet(predmet);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pomocná třída pro načítání JSON dat o NPC postavách.
     */
    private static class JsonNPC {
        String jmeno;
        String dialog;
        String NPCTyp;
        String mistnost;
        String hadanka;
        String odpoved;
        int zdravi;
        int sila;
        int kritikal;
    }

    /**
     * Pomocná třída pro načítání JSON dat o předmětech.
     */
    private static class JsonPredmet {
        String PredmetTyp;
        String nazev;
        String informace;
        int zdravi;
        int sila;
        int bonus;
        int doba;
        String typ;
        String mistnost;
        int npc;
    }
}
