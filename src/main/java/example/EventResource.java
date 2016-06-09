package example;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by user1 on 09.06.2016.
 */
@Path("/event")
@Produces("text/plain")
public class EventResource{

    private EntityManager em;
    private List<Event> listEvents;

    protected EntityManager getEntityManager() throws NamingException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tix");
        return emf.createEntityManager();
    }

    @GET
    public List<Event> getList() throws SQLException, NamingException {
        List records = getTixQuery();
        return records;
    }



    @GET
    @Path("{id}")
    public String getSingle(@PathParam("id") int id) throws NamingException {
        List records = getSingleQuery(id);
        return records.get(0).toString();
    }



    @POST
    public void insertTix(Event t) throws NamingException, SQLException {
        createQuery(t);
    }

    protected List<Event> getTixQuery() throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        listEvents = em.createQuery("SELECT e FROM Event e").getResultList();
        em.getTransaction().commit();
        em.close();
        return listEvents;
    }


    protected List getSingleQuery(int id) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        listEvents = Collections.singletonList(em.find(Event.class, id));
        em.getTransaction().commit();
        em.close();
        return listEvents;
    }


    protected void createQuery(Event t) throws SQLException, NamingException {
        Event event = new Event();
        em = getEntityManager();
        em.getTransaction().begin();
        event.setTitle(t.getTitle());
        event.setDesc("abcd");
        event.setUid(t.getUid());
        event.setCreated("1410876904");
        em.persist(event);
        em.getTransaction().commit();
        em.close();
    }

}