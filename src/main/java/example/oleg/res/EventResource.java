package example.oleg.res;

import example.oleg.beans.Event;
import example.oleg.dao.EventDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Produces("text/plain")
public class EventResource implements ResourceInterface<Event> {


    @Inject
    private EventDao eventDao;


    public List<Event> getAll() {
        return new ArrayList<Event>(eventDao.getAll());
    }


    public Event getOne(int id) {
        Event e = eventDao.getOne(id).get();
        return e;
    }

    public Response insert(Event t) {
        eventDao.create(t);
        URI loc = UriBuilder.fromPath("http://localhost:8080/jpa-ex/rest/loc/event").path("{id}").build(t.getId());
        return Response.created(loc).build();
    }


    public void delete(int id) {
        eventDao.delete(id);

    }

    @GET
    @Path("/db")
    @Produces("text/plain")
    public File getDbFile() {
        return new File("D:\\jpa\\rest_jpa_example\\src\\main\\resources\\test.db");
    }


}