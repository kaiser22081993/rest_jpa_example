package example.oleg.res;



import example.oleg.beans.Event;
import example.oleg.dao.EventDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.net.URI;
import java.util.List;


/**
 * Created by user1 on 09.06.2016.
 */

@Produces("text/plain")
public class EventResource implements ResourceInterface<Event>{


    @Inject
    private EventDao eventDao;


    public List<Event> getAll() {

        List<Event> records = (eventDao.getAll());
        return records;
    }


    public Event getOne(int id) {
        Event e = eventDao.getOne(id).get();
        return e;
    }

    public Response insert(Event t)  {
        eventDao.create(t);
        URI loc = UriBuilder.fromResource(EventResource.class).path("{id}").build(t.getId());
        return Response.created(loc).build();
    }


    public void delete(int id) {
        eventDao.delete(id);

    }

    @GET
    @Path("/db")
    @Produces("text/plain")
    public File getDbFile(){
        return new File("D:\\jpa\\rest_jpa_example\\src\\main\\resources\\test.db");
    }





}