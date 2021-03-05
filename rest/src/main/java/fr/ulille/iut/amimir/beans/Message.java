package fr.ulille.iut.amimir.beans;

import java.util.UUID;

import fr.ulille.iut.amimir.dto.MessageDto;

public class Message {
	private UUID id = UUID.randomUUID();
	private UUID author;
	private UUID dest;
	private String content;
	
	public Message() {
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public UUID getAuthor() {
		return author;
	}

	public void setAuthor(UUID author) {
		this.author = author;
	}

	public UUID getDest() {
		return dest;
	}

	public void setDest(UUID dest) {
		this.dest = dest;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public static MessageDto toDto(Message m) {
		MessageDto dto = new MessageDto();
		dto.setId(m.getId());
		dto.setAuthor(m.getAuthor());
		dto.setDest(m.getDest());
		dto.setContent(m.getContent());
		return dto;
	}
	
	public static Message fromDto(MessageDto dto) {
		Message m = new Message();
		m.setId(dto.getId());
		m.setAuthor(dto.getAuthor());
		m.setDest(dto.getDest());
		m.setContent(dto.getContent());
		return m;
	}
	
	public String toString() {
		return content;
	}
}
