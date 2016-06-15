package example;


import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class MyApp extends ResourceConfig {
    public MyApp() {
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(EventDao.class).to(EventDao.class);
            }
        });
    }
}
