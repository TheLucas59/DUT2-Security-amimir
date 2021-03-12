package fr.ulille.iut.amimir.json;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

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
	
	public static void serializeUser(User u) throws IOException {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("config/user.ser"));
		try {
			stream.writeObject(u);
		} finally {
			stream.close();
		}
	}
	
	public static void serializeContacts(List<Contact> c) throws IOException {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("config/contacts.ser"));
		try {
			stream.writeObject(c);
		} finally {
			stream.close();
		}
	}
	
	public static void serializeIdentityFile(User u) throws IOException {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("identityExport.ser"));
		try {
			stream.writeObject(IdentityExportBean.fromUser(u));
		} finally {
			stream.close();
		}
	}
	
	public static User deserializeUser(String url) throws IOException, ClassNotFoundException {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(url));
		User u = null;
		try {
			u = (User) stream.readObject();
		} finally {
			stream.close();
		}
		return u;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Contact> deserializeContacts(String url) throws IOException, ClassNotFoundException {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(url));
		List<Contact> l = null;
		try {
			l = (List<Contact>) stream.readObject();
		} finally {
			stream.close();
		}
		return l;
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
	
	public static Contact deserializeIdentityExport(String url, String alias) throws IOException, ClassNotFoundException {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(url));
		IdentityExportBean i = null;
		try {
			i = (IdentityExportBean) stream.readObject();
		} finally {
			stream.close();
		}
		return new Contact(i, alias);
	}
}
