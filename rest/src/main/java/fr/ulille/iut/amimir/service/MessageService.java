package fr.ulille.iut.amimir.service;

import fr.ulille.iut.amimir.BDDFactory;
import fr.ulille.iut.amimir.dao.MessageDAO;
import fr.ulille.iut.amimir.dto.MessageDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MessageService {
    private MessageDAO dao;

    public MessageService(){
        dao = BDDFactory.buildDao(MessageDAO.class);
        dao.createTable();
    }

    public List<MessageDTO> getAllMessagesById(String author, String dest) {
        return dao.getAllMessagesById(UUID.fromString(author), UUID.fromString(dest));
    }

    public UUID addNewMsg(MessageDTO message){
        UUID newId = UUID.randomUUID();
        dao.addNewMsg(message.getMessage(), message.getAuthor(), message.getDest(), newId, Date.valueOf(LocalDate.now()));
        return newId;
    }
}
