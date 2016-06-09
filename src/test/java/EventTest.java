import example.Event;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by user1 on 09.06.2016.
 */

public class EventTest {
    @Test
    public void crudTest(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tix");
        EntityManager manager = factory.createEntityManager();
        List<Event> events =  manager.createQuery("SELECT e FROM Event e").getResultList();
        Assert.assertEquals(12345678, events.get(0).getUid());
    }
}
