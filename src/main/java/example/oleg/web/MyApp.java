package example.oleg.web;


import example.oleg.dao.EventDao;
import example.oleg.dao.UserDao;
import example.oleg.res.EventResource;
import example.oleg.res.UserResource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest")
public class MyApp extends ResourceConfig {
    public MyApp() {
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(EventDao.class).to(EventDao.class);
                bind(EventResource.class).to(EventResource.class);
                bind(UserResource.class).to(UserResource.class);
                bind(UserDao.class).to(UserDao.class);
            }
        });
    }
}
