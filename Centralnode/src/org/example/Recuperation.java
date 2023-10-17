package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Recuperation {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Le serveur est en écoute sur le port 8080...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Nouvelle connexion établie avec le client " + socket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //System.out.println("Message reçu : " + inputLine);
                if (inputLine.contains("latitude") && inputLine.contains("longitude")) {
                	// Extraire les coordonnées de la chaîne JSON
                    String[] coordinates = inputLine.split(",");
                    String[] latString = coordinates[0].split(":");
                    String[] longString = coordinates[1].split(":");
                    String latitude = latString[1].replaceAll("\"", "").trim();
                    String longitude = longString[1].replaceAll("[^0-9.]", "").trim();
                    double latitude1 = Double.parseDouble(latitude);
                    double longitude1 = Double.parseDouble(longitude);
                    System.out.println("cette personne a les coordoonées suivantes: Latitude : "+latitude1+" longitude: "+ longitude1);
                    
                    //sendToNode(latitude1, longitude1);

                }

                             
            }

            socket.close();
        }
    }

        
    

    public static void sendToNode(double latitude, double longitude) throws IOException {
    	String IP_Node=null;
    	int PORT_Node=8081;
        try (Socket socket = new Socket(IP_Node, PORT_Node);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("Latitude: " + latitude + ", Longitude: " + longitude);
            System.out.println("Les coordonnées ont été envoyées au deuxième nœud pour vérifier leur cohérence"); 
        }
    }
}
