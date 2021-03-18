package fr.ulille.iut.amimir.commands;

import fr.ulille.iut.amimir.Client;
import fr.ulille.iut.amimir.beans.Contact;

public class Config {

	public static void handle(String[] args) {
		String importConfig = null;
		String configFolder = "./config/";
		boolean export = false;
		boolean listContacts = false;
		String removeContact = null;
		
		try{
			for(int i = 0; i < args.length; i++) {
				switch(args[i]) {
				case "--generate":
					Client client = new Client();
					client.generateUserConfiguration();
					System.out.println("Configuration générée.");
					System.out.println("Votre UUID est : " + client.u.getId());
					System.exit(0);
				case "-i":
				case "--import":
					importConfig = args[i+1];
					break;
				case "--config":
					configFolder = args[i+1];
					break;
				case "--export-identity":
					export = true;
					break;
				case "--list-contacts":
					listContacts = true;
					break;
				case "--remove":
					removeContact = args[i+1];
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			System.out.println("Erreur : mauvais arguments");
			System.exit(1);
		}
		
		Client client = new Client();
		client.loadUserConfiguration(configFolder);
		
		if(removeContact != null) {
			client.removeContact(removeContact);
			System.exit(0);
		}
		
		if(listContacts) {
			System.out.println("Liste des contacts : ");
			for(Contact c : client.l) {
				System.out.println("Contact " + c.getAlias() + "\nUUID : " + c.getId());
			}
			System.exit(0);
		}
		
		if(export) {
			client.exportIdentity();
			System.exit(0);
		}
		
		if(importConfig != null) {
			client.importContact(importConfig);
			System.exit(0);
		}
		
		System.out.println("Vous n'avez pas fait de commande!");
	}

}
