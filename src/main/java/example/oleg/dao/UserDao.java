package example.oleg.dao;

import example.oleg.beans.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user1 on 17.06.2016.
 */
@ApplicationScoped
public class UserDao implements DaoInterface<User> {

    private ConcurrentHashMap<Integer, User> db;
    private AtomicInteger countId;

    @Override
    @PostConstruct
    public void init() {
        db = new ConcurrentHashMap<>();
        countId = new AtomicInteger(0);
        int id = countId.incrementAndGet();
        User u = new User(id, "Oleh", "Email");
        db.put(id, u);
    }

    @Override
    public List getAll() {
        return new ArrayList<>(db.values());
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
        db.put(u.getId(), u);
    }


}
