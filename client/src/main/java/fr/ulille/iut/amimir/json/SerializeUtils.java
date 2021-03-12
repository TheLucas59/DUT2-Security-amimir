package fr.ulille.iut.amimir.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ulille.iut.amimir.Main;
import fr.ulille.iut.amimir.beans.Contact;
import fr.ulille.iut.amimir.beans.IdentityExportBean;
import fr.ulille.iut.amimir.beans.Message;
import fr.ulille.iut.amimir.beans.User;

public class SerializeUtils {
	public static ObjectMapper o = Main.o;
	
	/**
	 * Méthode pour sérialiser un User en JSON
	 * @param u Utilisateur à sérialiser
	 * @throws JsonGenerationException Le JSON n'a pas réussi à se générer
	 * @throws JsonMappingException Les données ne sont pas conformes au bean
	 * @throws IOException Le fichier n'a pas pu être écrit
	 */
	public static void serializeUser(User u) throws JsonGenerationException, JsonMappingException, IOException {
        o.writeValue(new File("config/user.json"), u);
	}
	
	/**
	 * Méthode pour sérialiser un Contact en JSON
	 * @param c Liste de contact à sérialiser
	 * @throws JsonGenerationException Le JSON n'a pas réussi à se générer
	 * @throws JsonMappingException Les données ne sont pas conformes au bean
	 * @throws IOException Le fichier n'a pas pu être écrit
	 */
	public static void serializeContacts(List<Contact> c) throws JsonGenerationException, JsonMappingException, IOException {
        o.writeValue(new File("config/contacts.json"), c);
	}
	
	/**
	 * Méthode pour exporter les parties publiques de l'utilisateur
	 * @param u Utilisateur à sérialiser
	 * @throws JsonGenerationException Le JSON n'a pas réussi à se générer
	 * @throws JsonMappingException Les données ne sont pas conformes au bean
	 * @throws IOException Le fichier n'a pas pu être écrit
	 */
	public static void serializeIdentityFile(User u) throws JsonGenerationException, JsonMappingException, IOException {
        o.writeValue(new File("identityExport.json"), IdentityExportBean.fromUser(u));
	}
	
	/**
	 * Méthode pour désérialiser un User depuis du JSON
	 * @param url Chemin vers le fichier contenant l'utilisateur
	 * @throws JsonParseException Le JSON n'a pas réussi à être parsé
	 * @throws JsonMappingException Les données ne sont pas conformes au bean
	 * @throws IOException Le fichier n'a pas pu être lu
	 */
	public static User deserializeUser(String url) throws JsonParseException, JsonMappingException, IOException {
		return o.readValue(new File(url), User.class);
	}
	
	/**
	 * Méthode pour désérialiser une liste de Contact depuis du JSON
	 * @param url Chemin vers le fichier contenant les contacts
	 * @throws JsonParseException Le JSON n'a pas réussi à être parsé
	 * @throws JsonMappingException Les données ne sont pas conformes au bean
	 * @throws IOException Le fichier n'a pas pu être lu
	 */
	public static List<Contact> deserializeContacts(String url) throws JsonParseException, JsonMappingException, IOException {
		return o.readValue(new File(url), new TypeReference<List<Contact>>(){});
	}
	
	/**
	 * Méthode pour désérialiser des messages depuis du JSON
	 * @param is InputStream correspondant à une requête vers le serveur REST
	 * @throws JsonParseException Le JSON n'a pas réussi à être parsé
	 * @throws JsonMappingException Les données ne sont pas conformes au bean
	 * @throws IOException Le fichier n'a pas pu être lu
	 */
	public static List<Message> deserializeMessage(InputStream is) throws JsonParseException, JsonMappingException, IOException {
		return o.readValue(is, new TypeReference<List<Message>>(){});
	}
	
	/**
	 * Méthode pour désérialiser un Contact depuis du JSON
	 * @param url Chemin vers le fichier contenant les contacts
	 * @param alias Alias a donner a ce nouveau Contact
	 * @throws JsonParseException Le JSON n'a pas réussi à être parsé
	 * @throws JsonMappingException Les données ne sont pas conformes au bean
	 * @throws IOException Le fichier n'a pas pu être lu
	 */
	public static Contact deserializeIdentityExport(String url, String alias) throws JsonParseException, JsonMappingException, IOException {
		return new Contact(o.readValue(new File(url), IdentityExportBean.class), alias);
	}
}
