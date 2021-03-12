package fr.ulille.iut.amimir.beans;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Message {
	private UUID author;
	private UUID dest;
	private String content;
	private UUID id;

	public Message() {
		
	}

	public Message(UUID author, UUID dest, String content, UUID id) {
		this.author = author;
		this.dest = dest;
		this.content = content;
		this.id = id;
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

	@JsonGetter
	public UUID getId() {
		return id;
	}

	@JsonSetter
	public void setId(UUID id) {
		this.id = id;
	}
}
