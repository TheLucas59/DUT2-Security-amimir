package fr.ulille.iut.amimir.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import fr.ulille.iut.amimir.BDDFactory;
import fr.ulille.iut.amimir.beans.Message;
import fr.ulille.iut.amimir.dao.MessageDao;
import fr.ulille.iut.amimir.dto.CreateMessageDto;
import fr.ulille.iut.amimir.dto.MessageDto;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageDto> getAll() {
    	return dao.getAll().stream().map(Message::toDto).collect(Collectors.toList());
    }
    
    @GET
    @Path("{author}/{dest}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageDto> getAllFromAuthorAndToDest(@PathParam("author") UUID author, @PathParam("dest") UUID dest){
    	return dao.findByAuthorDest(author, dest).stream().map(Message::toDto).collect(Collectors.toList());
    }
    
    @GET
    @Path("{id}")
    public Message getById(@PathParam("id") UUID id) {
    	return dao.findById(id);   	
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMessage(CreateMessageDto dto) {
    	try {
    		Message m = Message.fromCreateDto(dto);
    		dao.insert(m);
    		MessageDto d = Message.toDto(m);
    		URI uri = uriInfo.getAbsolutePathBuilder().path(m.getId().toString()).build();
    		
    		return Response.created(uri).entity(d).build();
    	} catch (Exception e) {
    		Logger.getLogger(MessageResource.class.getName()).severe(e.getMessage());
    		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    	}
    }
}
