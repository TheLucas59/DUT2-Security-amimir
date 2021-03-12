package fr.ulille.iut.amimir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import fr.ulille.iut.amimir.beans.Contact;
import fr.ulille.iut.amimir.beans.User;
import fr.ulille.iut.amimir.json.SerializeUtils;

public class Client {
	public User u;
	public List<Contact> l;
	
	public Client() {
		
	}
	
	/**
	 * Générer une nouvelle configuration et la sauvegarder sur le disque.
	 */
	public void generateUserConfiguration() {
		User u = new User(UUID.randomUUID());
		List<Contact> l = new ArrayList<Contact>();
		try {
			SerializeUtils.serializeUser(u);
			SerializeUtils.serializeContacts(l);
		} catch (IOException e) {
			System.out.println("Couldn't write to disk!");
			System.exit(1);
		}
		this.u = u;
		this.l = l;
	}
	
	/**
	 * Charge la configuration utilisateur a partir du dossier passé en paramètre
	 * @param configFolder
	 */
	public void loadUserConfiguration(String configFolder) {
		try {
			this.u = SerializeUtils.deserializeUser(configFolder + "user.ser");
			this.l = SerializeUtils.deserializeContacts(configFolder + "contacts.ser");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Couldn't read configuration!");
			System.exit(1);
		}
	}
	
	public void importContact(String file) {
		Scanner s = new Scanner(System.in);
		System.out.println("Nom pour ce contact?");
		String name = s.nextLine();
		try {
			SerializeUtils.deserializeIdentityExport(file, name);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Couldn't read configuration!");
			System.exit(1);
		}
		System.out.println("Contact " + name + " ajouté avec succès.");
		s.close();
	}

	public void exportIdentity() {
		try {
			SerializeUtils.serializeIdentityFile(u);
		} catch (IOException e) {
			System.out.println("Couldn't write to disk!");
			System.exit(1);
		}
		System.out.println("Fichier d'identité exporté avec succès.");
	}
	
	public UUID getDest(String dest) {
		try {
			return UUID.fromString(dest);
		} catch (IllegalArgumentException e) {
			for(Contact c : l) {
				if(c.getAlias().equals(dest)) {
					return c.getId();
				}
			}
		}
		return null;
	}
	
	public UUID getClientId() {
		return u.getId();
	}
	
	public byte[] encrypt(String message) {
		Encryption enc = new Encryption(u.getK().getPrivate());
		return enc.crypt(message);
	}
	
	public byte[] decrypt(String message) {
		Decryption dec = new Decryption(u.getK().getPublic());
		return dec.decrypt(message);
	}
}
