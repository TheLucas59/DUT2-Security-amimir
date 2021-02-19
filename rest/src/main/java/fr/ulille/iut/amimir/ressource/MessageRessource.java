package fr.ulille.iut.amimir.ressource;

import fr.ulille.iut.amimir.dto.MessageDTO;
import fr.ulille.iut.amimir.service.MessageService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessageRessource {
    private final static Logger LOGGER = Logger.getLogger(MessageRessource.class.getName());
    private MessageService service = new MessageService();

    @Context
    private UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{author}/to/{dest}")
    public List<MessageDTO> getAllMessagesById(@PathParam("author") String author, @PathParam("dest") String dest) {
        LOGGER.info("getAllMessagesById()");
        return service.getAllMessagesById(author, dest);
    }

    @POST
    public Response addNewMsg(MessageDTO message){
        try{
            UUID newId = service.addNewMsg(message);
            URI uri = uriInfo.getAbsolutePathBuilder().path(newId.toString()).build();
            return Response.created(uri).entity(message).build();
        } catch (Exception e){
            e.printStackTrace();
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        }
    }
}
