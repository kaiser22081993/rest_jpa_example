package example.oleg.dao;

import java.util.List;
import java.util.Optional;


public interface DaoInterface2<T> {
    public void init();
    public List getAll();
    public void delete(int id);
    public Optional<T> getOne(int id);
    public void create(T t);

}