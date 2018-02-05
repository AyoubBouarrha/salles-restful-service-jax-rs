/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import client.SalleServiceRESTfromDBClient;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import jpa.Salle;

/**
 *
 * @author YB
 */
public class SalleServiceRESTfromDBClinetJSON {

    public void getSallesJSON(SalleServiceRESTfromDBClient client) {
        String response = client.findAll_JSON(String.class);
        System.out.println("### Reponse json : \n  \t" + response);

        JsonArray sallesArray;
        try (JsonReader reader = Json.createReader(new StringReader(response))) {
            sallesArray = reader.readArray();

            System.out.println("\n### Liste des salles :");
            System.out.println("-----------------------------");
            for (Object obj : sallesArray) {
                JsonObject salle = (JsonObject) obj;
                System.out.println("idSalle : " + salle.get("idsalle"));
                System.out.println("Capacite : " + salle.get("capacite"));
                System.out.println("Nom : " + salle.get("nomsalle").toString().replace("\"", ""));
                String dispoProjecteur = salle.get("proj").toString().equals("1") ? "oui" : "non";
                System.out.println("Projecteur vidéo : " + dispoProjecteur);
                System.out.println("Type : " + salle.get("typesalle").toString().replace("\"", ""));
                System.out.println("-----------------------------");
            }
        }
    }

    public Salle getSalleByIdJSON(SalleServiceRESTfromDBClient client, String idSalle) {
        String response = client.find_JSON(String.class, idSalle);
        System.out.println("### Reponse json : \n  \t" + response);

        JsonObject salleObj;

        try (JsonReader reader = Json.createReader(new StringReader(response))) {
            try {
                salleObj = reader.readObject();

                JsonObject salle = salleObj;

                if (salle.isEmpty()) {
                    System.out.println("### Aucune salle trouvé avec l'Id: " + idSalle);
                    return null;
                } else {
                    System.out.println("\n### Salle trouvé :");
                    System.out.println("Id : " + salle.get("idsalle"));
                    System.out.println("Capacite : " + salle.get("capacite"));
                    System.out.println("Nom : " + salle.get("nomsalle").toString().replace("\"", ""));
                    String dispoProjecteur = salle.get("proj").toString().equals("1") ? "oui" : "non";
                    System.out.println("Projecteur vidéo : " + dispoProjecteur);
                    System.out.println("Type : " + salle.get("typesalle").toString().replace("\"", ""));
                    System.out.println("-----------------------------");

                    Salle salleResult = new Salle(Integer.parseInt(salle.get("idsalle").toString()));
                    salleResult.setNomsalle(salle.get("nomsalle").toString().replace("\"", ""));
                    salleResult.setTypesalle(salle.get("typesalle").toString().replace("\"", ""));
                    salleResult.setCapacite(Integer.valueOf(salle.get("capacite").toString()));
                    salleResult.setProj(Short.valueOf(salle.get("proj").toString()));
                    return salleResult;
                }
            } catch (JsonParsingException ex) {
                System.out.println("### Aucune salle trouvé avec l'Id: " + idSalle);
                return null;
            }

        }
    }

    public void getSalleByTypeJSON(SalleServiceRESTfromDBClient client, String typeSalle) {
        String response = client.findByTypeC_JSON(String.class, typeSalle);
        System.out.println("### Reponse json : \n  \t" + response);

        JsonArray sallesArray;
        try (JsonReader reader = Json.createReader(new StringReader(response))) {
            sallesArray = reader.readArray();
            if (sallesArray.isEmpty()) {
                System.out.println("### Aucune salle trouvé avec le type " + typeSalle);
            } else {
                System.out.println("\n### Liste des salles de type " + typeSalle + " trouvés :");
                for (Object obj : sallesArray) {
                    JsonObject salle = (JsonObject) obj;
                    System.out.println("idSalle : " + salle.get("idsalle"));
                    System.out.println("Capacite : " + salle.get("capacite"));
                    System.out.println("Nom : " + salle.get("nomsalle").toString().replace("\"", ""));
                    String dispoProjecteur = salle.get("proj").toString().equals("1") ? "oui" : "non";
                    System.out.println("Projecteur vidéo : " + dispoProjecteur);
                    System.out.println("Type : " + salle.get("typesalle").toString().replace("\"", ""));
                    System.out.println("-----------------------------");
                }
            }

        }
    }

    public void addSalleJSON(SalleServiceRESTfromDBClient client, Salle salle) {
        client.create_JSON(salle);
    }

    public void updateSalleJSON(SalleServiceRESTfromDBClient client, Salle salle, String idSalle) {
        client.edit_JSON(salle, idSalle);
    }

    public void deleteSalleJSON(SalleServiceRESTfromDBClient client, String idSalle) {
        client.remove(idSalle);
    }
}
