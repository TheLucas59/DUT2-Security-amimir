package fr.ulille.iut.amimir.resources;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import fr.ulille.iut.amimir.BDDFactory;
import fr.ulille.iut.amimir.beans.Message;
import fr.ulille.iut.amimir.dao.MessageDao;
import fr.ulille.iut.amimir.dto.MessageDto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;

@Path("/messages")
public class MessageResource {
	private MessageDao dao;
	
    @Context
    public UriInfo uriInfo;

    public MessageResource() {
    	dao = BDDFactory.buildDao(MessageDao.class);
    	dao.createTable();
    }

    @GET
    @Produces("application/json")
    public List<MessageDto> getAll() {
    	return dao.getAll().stream().map(Message::toDto).collect(Collectors.toList());
    }
    
    @GET
    @Path("{author}/{dest}")
    @Produces("application/json")
    public List<MessageDto> getAllFromAuthorAndToDest(@PathParam("author") UUID author, @PathParam("dest") UUID dest){
    	return dao.findByAuthorDest(author, dest).stream().map(Message::toDto).collect(Collectors.toList());
    }
}
