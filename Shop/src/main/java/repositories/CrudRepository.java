package repositories;

import models.User;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    boolean save(T t);
    void update(T t);
    Optional<T> find(T t);
    boolean delete(T t);

    List<T> findAll();
}

