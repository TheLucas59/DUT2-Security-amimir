package fr.ulille.iut.amimir.beans;

import java.security.PublicKey;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class IdentityExportBean {
	PublicKey pubkey;
	UUID id;
	
	IdentityExportBean(){
		
	}

	public IdentityExportBean(PublicKey pubkey, UUID id) {
		this.pubkey = pubkey;
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
	public UUID getId() {
		return id;
	}

	@JsonSetter
	public void setId(UUID id) {
		this.id = id;
	}
	
	public static IdentityExportBean fromUser(User u) {
		return new IdentityExportBean(u.getK().getPublic(), u.getId());
	}
}
