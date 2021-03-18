package fr.ulille.iut.amimir.dto;

import java.util.UUID;

public class CreateMessageDto {
	private UUID author;
	private UUID dest;
	private String content;
	
	public CreateMessageDto() {
		
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

}
