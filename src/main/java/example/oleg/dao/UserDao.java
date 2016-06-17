package example.oleg.dao;

import example.oleg.beans.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


@ApplicationScoped
public class UserDao implements DaoInterface2<User> {

    private static ConcurrentHashMap<Integer, User> db  = new ConcurrentHashMap<>();
    Logger LOG = Logger.getLogger(UserDao.class.getSimpleName());

    @PostConstruct
    public void init() {
        User u = new User(0, "Oleh", "Email");
        db.put(u.getId(), u);
    }

    @Override
    public List getAll() {
        LOG.info("GET: USER DAO SIZE ----" + db.size() + " map " + db.hashCode());
        List<User> users = new ArrayList<>(db.values());
        LOG.info("GET: USER DAO SIZE ----" + db.size() + " map " + db.hashCode());
        return users;
    }

    @Override
    public void delete(int id) {
        db.remove(id);
    }

    @Override
    public Optional<User> getOne(int id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public void create(User u) {
        LOG.info("Attempt to create user vs ID: ----" + u.getId());
        db.put(u.getId(), u);
        LOG.info("map SIZE: " + db.size() + "--hash " + db.hashCode());
    }


}
