package fr.ulille.iut.amimir.commands;

import fr.ulille.iut.amimir.Client;

public class Config {

	public static void handle(String[] args) {
		Client client = new Client();
		client.generateUserConfiguration();
	}

}
