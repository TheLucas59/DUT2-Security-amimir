package fr.ulille.iut.amimir.beans;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class User {
	private PrivateKey privkey;
	private PublicKey pubkey;
	private UUID id;
	
	public User() {
		
	}
	
	public User(PrivateKey privkey, PublicKey pubkey, UUID id) {
		this.privkey = privkey;
		this.pubkey = pubkey;
		this.id = id;
	}
	
	@JsonGetter
	public PrivateKey getPrivkey() {
		return privkey;
	}
	
	@JsonSetter
	public void setPrivkey(PrivateKey privkey) {
		this.privkey = privkey;
	}
	
	@JsonGetter
	public PublicKey getPubkey() {
		return pubkey;
	}
	
	@JsonSetter
	public void setPubkey(PublicKey pubkey) {
		this.pubkey = pubkey;
	}
	
	@JsonGetter
	public UUID getId() {
		return id;
	}
	
	@JsonSetter
	public void setId(UUID id) {
		this.id = id;
	}
}
