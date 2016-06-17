package example.oleg;

import example.oleg.res.EventResource;
import example.oleg.res.ResourceInterface;
import example.oleg.res.UserResource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by user1 on 17.06.2016.
 */
@Path("/loc")
public class ResourceLocator {

    @Inject
    EventResource eventResource;

    @Inject
    UserResource userResource;

    @Path("{p}")
    public ResourceInterface locate(@PathParam("p")String param) {
        switch (param){
            case "event":
                return eventResource;
            case "user":
                return userResource;
            default:
                return eventResource;
        }

    }
}
