package fr.ulille.iut.amimir.dto;

import sun.plugin2.message.Message;

import java.util.UUID;

public class MessageDTO {
    private String message;
    private UUID author;
    private UUID dest;

    MessageDTO(String message, UUID author, UUID dest){
        this.message = message;
        this.author = author;
        this.dest = dest;
    }

    MessageDTO() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

}
