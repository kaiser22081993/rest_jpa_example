import example.oleg.beans.Event;
import example.oleg.dao.EventDao;
import javassist.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Optional;

/**
 * Created by user1 on 09.06.2016.
 */

public class DaoTest {

    public void crudTest() throws NotFoundException {
        EventDao dao = new EventDao();

        Event e = new Event();
        e.setId(2);
        e.setTitle("t2");
        e.setUid(1234);
        e.setCreated("cr2");
        e.setDesc("d2");
        dao.create(e);

        Optional<Event> event = dao.getOne(2);

        event.orElseThrow(()-> new NotFoundException("Event not found"));

        dao.delete(e.getId());

    }

    public void hibernateFuncTest(){
        EntityManager em = Persistence.createEntityManagerFactory("Tix").createEntityManager();
        Event e = em.find(Event.class,1);
        System.out.println(e);


    }

}
