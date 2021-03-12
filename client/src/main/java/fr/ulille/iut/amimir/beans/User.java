package fr.ulille.iut.amimir.beans;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import fr.ulille.iut.amimir.KeyGenerator;

public class User implements Serializable{
	private static final long serialVersionUID = 2829554531572939493L;
	private KeyPair k;
	private UUID id;
	
	public User() {
		
	}
	
	public User(UUID id) {
		try {
			this.k = KeyGenerator.buildKeyPair();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Couldn't create key pair");
			System.exit(1);
		}
		this.id = id;
	}
	
	public User(KeyPair k, UUID id) {
		this.k = k;
		this.id = id;
	}
	
	@JsonGetter
	public UUID getId() {
		return id;
	}
	
	@JsonSetter
	public void setId(UUID id) {
		this.id = id;
	}

	public KeyPair getK() {
		return k;
	}

	public void setK(KeyPair k) {
		this.k = k;
	}
}
