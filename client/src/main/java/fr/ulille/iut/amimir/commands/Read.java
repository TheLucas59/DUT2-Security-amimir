package fr.ulille.iut.amimir.commands;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ulille.iut.amimir.Client;
import fr.ulille.iut.amimir.Main;
import fr.ulille.iut.amimir.beans.Message;
import fr.ulille.iut.amimir.json.SerializeUtils;

public class Read {
	public static ObjectMapper o = Main.o;

	public static void handle(String[] args) {
		String server = "http://localhost:8080/api/v1/messages";
		String configFolder = "./config/";
		String authorId = null;

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
		UUID resolvedAuthorId = client.getDest(authorId);
		if(authorId == null) {
			System.out.println("Erreur : contact introuvable");
			System.exit(1);
		}
		
		try {
			List<Message> l = SerializeUtils.deserializeMessage(new URL(server + "/" + resolvedAuthorId.toString() + "/" + destId.toString()).openStream());
			for(Message m : l) {
				System.out.println(new String(client.decrypt(m.getContent())));
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
