package example;

import com.sun.jersey.api.NotFoundException;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by user1 on 09.06.2016.
 */
@Path("/event")
@Produces("text/plain")
public class EventResource {


    @Inject
    private EventDao eventDao;

/*    public EventResource(){
        eventDao = new EventDao();
    }*/
    @GET
    @Produces("application/xml")
    public List<Event> getAllEvents() {

        List<Event> records = (eventDao.getAll());
        return records;
    }

    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Event getSingleEvent(@PathParam("id") int id) {
        Event e = eventDao.getEvent(id).get();
        return e;
    }
    @POST
    public Response insertEvent(Event t)  {
        eventDao.create(t);
        URI loc = UriBuilder.fromResource(EventResource.class).path("{id}").build(t.getId());
        return Response.created(loc).build();
    }

    @DELETE
    @Path("{id}")
    public void deleteEvent(@PathParam("id") int id) {
        eventDao.delete(id);

    }





}