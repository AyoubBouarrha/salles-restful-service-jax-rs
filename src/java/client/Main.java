/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpa.Salle;
import json.SalleServiceRESTfromDBClinetJSON;
import json.SalleServiceRESTfromDBClinetXML;

/**
 *
 * @author YB
 */
public class Main {

    static SalleServiceRESTfromDBClient client = new SalleServiceRESTfromDBClient();
    static SalleServiceRESTfromDBClinetJSON jsonService = new SalleServiceRESTfromDBClinetJSON();

    public static void main(String[] args) { 
        int numChoix = -1;

        do {
            try {
                numChoix = showMenu();

                switch (numChoix) {
                    case 1:

                        listeSalles();

                        break;
                    case 2:

                        chercheParId();

                        break;
                    case 3:

                        chercheParType();

                        break;
                    case 4:

                        ajouterSalle();

                        break;
                    case 5:

                        modifierSalle();

                        break;
                    case 6:

                        supprimerSalle();

                        break;

                }

                System.out.print("Appuyer sur n'importe quelle touche pour continuer ...");
                System.in.read();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (numChoix != 0);
        System.out.println("Fin du programme !");

    }  
    
    
    public static int showMenu() {

        int numchoix = -1;
        do {
            System.out.println("\n\n\n\n\n");
            System.out.println("######################################################");
            System.out.println("1- Lister l'ensemble des Salles");
            System.out.println("2- Recherche une salle par id");
            System.out.println("3- Recherche une salle par type");
            System.out.println("4- Ajouter une salle");
            System.out.println("5- Mettre à jour les informations d'une salle");
            System.out.println("6- Supprimer une salle");
            System.out.println("######################################################");

            System.out.print("Veuillez choisir une opération entre 1 et 7 (0 pour terminer): ");
            numchoix = new Scanner(System.in).nextInt();

        } while (numchoix > 7 || numchoix < 0);

        return numchoix;

    }

    public static void listeSalles() {
        jsonService.getSallesJSON(client);
    }

    public static void chercheParId() {

        System.out.print("Veuillez entrer l'id de la salle à chercher : ");
        int idSalle = new Scanner(System.in).nextInt();
        jsonService.getSalleByIdJSON(client, String.valueOf(idSalle));

    }

    public static void chercheParType() {

        System.out.print("Veuillez entrer le type de la salle à chercher : ");
        String typeSalle = new Scanner(System.in).nextLine();
        jsonService.getSalleByTypeJSON(client, String.valueOf(typeSalle));

    }

    public static void ajouterSalle() {

        System.out.println("Veuillez entrer les informations de la salle à ajouter : ");

        System.out.print("Capacité de la salle :");
        int capSalle = Integer.parseInt(new Scanner(System.in).next());

        System.out.print("Nom de la salle :");
        String nomSalle = new Scanner(System.in).nextLine();

        System.out.print("Salle avec Projecteur Vidéo (o/n):");
        String valueProj = new Scanner(System.in).next();
        valueProj = valueProj.equals("o") || valueProj.equals("O") ? "1" : "0";
        short projSalle = Short.valueOf(valueProj);

        System.out.print("Type de la salle :");
        String typeSalle = new Scanner(System.in).nextLine();

        Salle salle = new Salle(nomSalle, typeSalle, projSalle, capSalle);
        jsonService.addSalleJSON(client, salle);

        System.out.println("\nSalle Ajoutée avec succès ! ");

    }

    public static void modifierSalle() {

        System.out.print("Veuillez entrer l'id de la salle à modifer : ");
        int idSalle = new Scanner(System.in).nextInt();
        Salle salle = jsonService.getSalleByIdJSON(client, String.valueOf(idSalle));
        if (salle != null) {
            String valeurSaisi;
            System.out.println("Veuillez entrer les nouvelles informations de la salle à modifier : ");

            System.out.print("Nom de la salle (saisir '.'  pour maintenir l'ancienne valeur):");
            valeurSaisi = new Scanner(System.in).nextLine();
            salle.setNomsalle(valeurSaisi.equals(".") ? salle.getNomsalle() : valeurSaisi);

            System.out.print("Capacité de la salle (saisir '.' pour maintenir l'ancienne valeur) :");
            valeurSaisi = new Scanner(System.in).next();
            salle.setCapacite(valeurSaisi.equals(".") ? salle.getCapacite() : Integer.parseInt(valeurSaisi));

            System.out.print("Type de la salle (saisir '.'  pour maintenir l'ancienne valeur):");
            valeurSaisi = new Scanner(System.in).nextLine();
            salle.setTypesalle(valeurSaisi.equals(".") ? salle.getTypesalle() : valeurSaisi);

            System.out.print("Salle avec Projecteur Vidéo (o/n) (saisir '.'  pour maintenir l'ancienne valeur):");
            valeurSaisi = new Scanner(System.in).next();
            String valueProj = valeurSaisi.equals(".") ? String.valueOf(salle.getProj()) : valeurSaisi;
            valueProj = valueProj.equals("o") || valueProj.equals("O") || valueProj.equals("1") ? "1" : "0";
            salle.setProj(Short.valueOf(valueProj));

            jsonService.updateSalleJSON(client, salle, String.valueOf(idSalle));

            System.out.println("\nSalle Modifié avec succès ! ");
        }

    }

    public static void supprimerSalle() {

        System.out.print("Veuillez entrer l'id de la salle à supprimer : ");
        int idSalle = new Scanner(System.in).nextInt();
        Salle salle = jsonService.getSalleByIdJSON(client, String.valueOf(idSalle));
        if (salle != null) {
            jsonService.deleteSalleJSON(client, String.valueOf(idSalle));
            System.out.println("\nSalle Supprimé avec succès ! ");
        }

    }

}
