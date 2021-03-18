package fr.ulille.iut.amimir;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ulille.iut.amimir.commands.Config;
import fr.ulille.iut.amimir.commands.Read;
import fr.ulille.iut.amimir.commands.Send;

public class Main {
	public static ObjectMapper o = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	
	public static void main(String[] args) {
		if(args.length < 1) {
			printTodo();
			System.exit(1);
		}
		
		switch(args[0]) {
			case "read":
				Read.handle(args);
				break;
			case "send":
				Send.handle(args);
				break;
			case "config":
				Config.handle(args);
				break;
			default:
				printTodo();
		}
	}

	private static void printTodo() {
		System.out.println("Amimir - Client\n"
				+ "Utilisation : \n"
				+ "read : Lire des messages\n"
				+ "		-a <auteur> (UUID / Contact) : Auteur du message\n"
				+ "send : envoyer un message\n"
				+ "		-d <contact> : Destinataire du message\n"
				+ "		-m <message> : Message a envoyer\n"
				+ "config : gerer la configuration\n"
				+ "		--generate : Crée une nouvelle configuration dans le dossier 'config' ou est situé le jar du client.\n"
				+ "		--import <chemin> : Ajoute au contact le contact contenu dans un fichier.\n"
				+ "		--export-identity : Exporte la configuration actuelle ou est situé le jar du client.\n"
				+ "\n"
				+ "Arguments globaux : \n"
				+ "		-s, --server <server url> : Spécifier un serveur différent du serveur par défaut (localhost)\n"
				+ "		--config <config folder> : Spécifier un dossier de configuration différent de celui par défaut (./config)\n");
	}
}
