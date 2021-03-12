package fr.ulille.iut.amimir.commands;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ulille.iut.amimir.Client;
import fr.ulille.iut.amimir.Main;

public class Read {
	public static ObjectMapper o = Main.o;

	public static void handle(String[] args) {
		String server = "http://localhost:8080/api/v1/messages";
		String configFolder = "./config/";
		String authorId = null;
		String message = null;

		try{
			for(int i = 0; i < args.length; i++) {
				switch(args[i]) {
				case "-s":
				case "--server":
					server = args[i+1];
					break;
				case "-a":
					authorId = args[i+1];
					break;
				case "--config":
					configFolder = args[i+1];
					break;
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			System.out.println("Erreur : mauvais arguments");
			System.exit(1);
		}
		
		if(authorId == null) {
			System.out.println("Erreur : arguments manquants");
			System.exit(1);
		}
		
		Client client = new Client();
		client.loadUserConfiguration(configFolder);
		
		UUID destId = client.getClientId();
		if(authorId == null) {
			System.out.println("Erreur : contact introuvable");
			System.exit(1);
		}
		
		try {
			URL url = new URL(server + "/" + authorId + "/" + destId.toString());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			
			InputStream is = con.getInputStream();
			byte[] output = is.readAllBytes();
			String cryptedMessage = new String(output);
			System.out.println(cryptedMessage);
			byte[] messageBytes = client.decrypt(cryptedMessage);
			message = new String(messageBytes);
			System.out.println(message);
			
			if(con.getResponseCode() == 200) {
				System.out.println("Affichage des messages reçus!");
			} else {
				System.out.println("Erreur lors de la réception des messages, le serveur a renvoyé : " + con.getResponseCode());
			}
		} catch (MalformedURLException e) {
			System.out.println("Erreur : mauvaise URL de serveur");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Erreur : le serveur n'a pas accepté la requête");
			System.exit(1);
		}
	}
}
