package fr.ulille.iut.amimir.beans;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MessagePostBean {
	private UUID author;
	private UUID dest;
	private String content;
	
	public MessagePostBean() {
		
	}
	
	public MessagePostBean(UUID author, UUID dest, String content) {
		this.author = author;
		this.dest = dest;
		this.content = content;
	}

	@JsonGetter
	public UUID getAuthor() {
		return author;
	}

	@JsonSetter
	public void setAuthor(UUID author) {
		this.author = author;
	}

	@JsonGetter
	public UUID getDest() {
		return dest;
	}

	@JsonSetter
	public void setDest(UUID dest) {
		this.dest = dest;
	}

	@JsonGetter
	public String getContent() {
		return content;
	}

	@JsonSetter
	public void setContent(String content) {
		this.content = content;
	}
}
