/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import client.SalleServiceRESTfromDBClient;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author YB
 */
public class SalleServiceRESTfromDBClinetXML {

    public void getSallesXML(SalleServiceRESTfromDBClient client) {
        try {
            String response = client.findAll_XML(String.class);
            System.out.println("### Reponse xml : \n  \t" + response);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(response)));

            NodeList nList = doc.getElementsByTagName("salle");

            System.out.println("\n### Liste des salles :");
            System.out.println("-----------------------------");

            for (int index = 0; index < nList.getLength(); index++) {
                Node nNode = nList.item(index);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                   
                    System.out.println("idSalle : " + eElement.getElementsByTagName("idsalle").item(0).getTextContent());
                    System.out.println("Capacite : " + eElement.getElementsByTagName("capacite").item(0).getTextContent());
                    System.out.println("Nom : " + eElement.getElementsByTagName("nomsalle").item(0).getTextContent().replace("\"", ""));
                    String dispoProjecteur = eElement.getElementsByTagName("proj").item(0).getTextContent().equals("1") ? "oui" : "non";
                    System.out.println("Projecteur vidéo : " + dispoProjecteur);
                    System.out.println("Type : " + eElement.getElementsByTagName("typesalle").item(0).getTextContent().replace("\"", ""));
                    System.out.println("-----------------------------");
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(SalleServiceRESTfromDBClinetXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
