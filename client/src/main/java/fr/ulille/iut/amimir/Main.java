package fr.ulille.iut.amimir;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ulille.iut.amimir.beans.Message;
import fr.ulille.iut.amimir.json.SerializeUtils;

public class Main {
	public static ObjectMapper o = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	
	public static void main(String[] args) {
		List<Message> l;
		try {
			l = SerializeUtils.deserializeMessage(new URL(args[0]).openStream());
			System.out.println(l);
		} catch (JsonParseException e) {
			System.out.println("Couldn't parse JSON received from server!");
		} catch (JsonMappingException e) {
			System.out.println("Couldn't map JSON to bean!");
		} catch (MalformedURLException e) {
			System.out.println("Couldn't read this URL!");
		} catch (IOException e) {
			System.out.println("Couldn't get this URL!");
		}
	}
}
