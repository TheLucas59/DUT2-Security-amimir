package fr.ulille.iut.amimir.beans;

import java.security.PublicKey;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Contact {
	private PublicKey pubkey;
	private String alias;
	
	public Contact() {
		
	}
	
	public Contact(PublicKey pubkey, String alias){
		this.pubkey = pubkey;
		this.alias = alias;
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
}
