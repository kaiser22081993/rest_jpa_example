package example.oleg.res;

import example.oleg.ResourceLocator;
import example.oleg.beans.User;
import example.oleg.dao.UserDao;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * Created by user1 on 17.06.2016.
 */
public class UserResource implements ResourceInterface<User> {

    @Inject
    UserDao dao;


    @Override
    public List<User> getAll() {
        List<User> users = dao.getAll();
        return users;
    }

    @Override
    public Response insert(User u) {
        dao.create(u);
        URI loc = UriBuilder.fromPath("http://localhost:8080/jpa-ex/loc/user").path("{id}").build(u.getId());
        return Response.created(loc).build();
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public User getOne(int id) {
        return dao.getOne(id).get();
    }


}
