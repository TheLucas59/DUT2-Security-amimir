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
			System.out.println("Usage : //TODO");
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
		}
	}
}
