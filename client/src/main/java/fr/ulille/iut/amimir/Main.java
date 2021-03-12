package fr.ulille.iut.amimir;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ulille.iut.amimir.beans.Contact;
import fr.ulille.iut.amimir.beans.Message;
import fr.ulille.iut.amimir.beans.User;

public class Main {
	public static ObjectMapper o = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		List<Message> l = deserializeMessage(new URL("http://localhost:8080/api/v1/messages/").openStream());
		System.out.println(l);
	}
	
	
	/**
	 * Méthode pour sérialiser un User en JSON
	 * @param u Utilisateur à sérialiser
	 * @throws JsonGenerationException Le JSON n'a pas réussi à se générer
	 * @throws JsonMappingException Les données ne sont pas conformes au bean
	 * @throws IOException Le fichier n'a pas pu être lu
	 */
	public static void serializeUser(User u) throws JsonGenerationException, JsonMappingException, IOException {
        o.writeValue(new File("config/user.json"), u);
	}
	
	/**
	 * Méthode pour sérialiser un Contact en JSON
	 * @param c Liste de contact à sérialiser
	 * @throws JsonGenerationException Le JSON n'a pas réussi à se générer
	 * @throws JsonMappingException Les données ne sont pas conformes au bean
	 * @throws IOException Le fichier n'a pas pu être lu
	 */
	public static void serializeContacts(List<Contact> c) throws JsonGenerationException, JsonMappingException, IOException {
        o.writeValue(new File("config/contacts.json"), c);
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
	 * Méthode pour désérialiser un Contact depuis du JSON
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
}
