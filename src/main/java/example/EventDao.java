package example;



import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;


@ApplicationScoped
public class EventDao {

    private EntityManager manager;
    private Logger LOGGER = Logger.getLogger(EventDao.class.getName());

    public EventDao(){
       init();
    }
    public void init(){
        manager = Persistence.createEntityManagerFactory("Tix").createEntityManager();
    }

    public List getAll(){

        return (manager.createQuery("select e from Event e").getResultList());
    }
    public Optional<Event> getEvent(int id){
        Event e = manager.find(Event.class,id);
        LOGGER.info("get: " + e);
        return Optional.ofNullable(e);

    }
    public boolean contains(Event e){
        List<Event> events = manager
                .createNativeQuery("SELECT * FROM tix_event WHERE id = " + e.getId() + ";")
                .getResultList();
        return events.size() > 0;
    }
    public void create(Event e){
        Objects.requireNonNull(e);
        if(contains(e)){
            return;
        }
        manager.getTransaction().begin();
        manager.persist(e);
        manager.getTransaction().commit();
        LOGGER.info("created: " + e);

    }

    public void delete(int id) {
        manager.getTransaction().begin();
        manager.remove(manager.find(Event.class,id));
        manager.getTransaction().commit();
    }
}
