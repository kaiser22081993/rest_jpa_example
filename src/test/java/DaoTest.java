import example.Event;
import example.EventDao;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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

        Optional<Event> event = dao.getEvent(2);

        event.orElseThrow(()-> new NotFoundException("Event not found"));

        dao.delete(e.getId());

    }
}
