package fr.ulille.iut.amimir.beans;

import java.security.PublicKey;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Contact {
	private PublicKey pubkey;
	private UUID id;
	private String alias;
	
	public Contact() {
		
	}
	
	public Contact(IdentityExportBean e, String alias) {
		this.pubkey = e.getPubkey();
		this.id = e.getId();
		this.alias = alias;
	}
	
	public Contact(PublicKey pubkey, String alias, UUID id){
		this.pubkey = pubkey;
		this.alias = alias;
		this.id = id;
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
	public String getAlias() {
		return alias;
	}
	
	@JsonSetter
	public void setAlias(String alias) {
		this.alias = alias;
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
