package fr.ulille.iut.amimir.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ulille.iut.amimir.Client;
import fr.ulille.iut.amimir.Main;
import fr.ulille.iut.amimir.beans.MessagePostBean;

public class Send {
	public static ObjectMapper o = Main.o;
	
	public static void handle(String[] args) {
		String server = "http://localhost:8080/api/v1/messages";
		String configFolder = "./config/";
		String dest = null;
		String message = null;
		
		try{
			for(int i = 0; i < args.length; i++) {
				switch(args[i]) {
				case "-s":
				case "--server":
					server = args[i+1];
					break;
				case "-d":
					dest = args[i+1];
					break;
				case "--config":
					configFolder = args[i+1];
					break;
				case "-m":
					message = args[i+1];
					break;
				}
			}
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			System.out.println("Erreur : mauvais arguments");
			System.exit(1);
		}
		
		if(dest == null || message == null) {
			System.out.println("Erreur : arguments manquants");
			System.exit(1);
		}
		
		Client client = new Client();
		client.loadUserConfiguration(configFolder);
		
		UUID destId = client.getDest(dest);
		if(destId == null) {
			System.out.println("Erreur : contact introuvable");
			System.exit(1);
		}
		UUID authorId = client.getClientId();
		
		byte[] encryptedMessage = client.encrypt(message);
		
		MessagePostBean m = new MessagePostBean(authorId, destId, encryptedMessage.toString());
		
		try {
			URL url = new URL(server);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			
			OutputStream os = con.getOutputStream();
			byte[] input = o.writeValueAsString(m).getBytes("utf-8");
			os.write(input, 0, input.length);
		} catch (MalformedURLException e) {
			System.out.println("Erreur : mauvaise URL de serveur");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Erreur : le serveur n'a pas accepté la requête");
			System.exit(1);
		}
	}

}
