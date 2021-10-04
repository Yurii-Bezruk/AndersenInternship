package striker.studing.database;

import java.util.List;

public interface DAO<T> {
    T create(T t);
    List<T> readAll();
    T read(long id);
    void update(T t);
    void delete(T t);
}
