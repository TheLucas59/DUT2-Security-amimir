package fr.ulille.iut.amimir.beans;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Message{
	private UUID author;
	private UUID dest;
	private String content;
	private UUID id;
	private long timestamp;

	public Message() {
		
	}

	public Message(UUID author, UUID dest, String content, UUID id, long timestamp) {
		this.author = author;
		this.dest = dest;
		this.content = content;
		this.id = id;
		this.timestamp = timestamp;
	}
	
	public Message(UUID author, UUID dest, String content, long timestamp) {
		this.author = author;
		this.dest = dest;
		this.content = content;
		this.id = UUID.randomUUID();
		this.timestamp = timestamp;
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

	@Override
	public String toString() {
		return "Message [author=" + author + ", dest=" + dest + ", content=" + content + ", id=" + id + "]";
	}

	@JsonGetter
	public long getTimestamp() {
		return timestamp;
	}

	@JsonSetter
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
