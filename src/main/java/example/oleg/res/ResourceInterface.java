package example.oleg.res;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

public interface ResourceInterface<T> {
    @GET
    @Produces("application/xml")
    public List<T> getAll();

    @POST
    @Consumes("application/xml")
    public Response insert(T t);

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id);

    @GET
    @Path("{id}")
    @Produces("application/xml")
    public T getOne( @PathParam("id") int id);
}
