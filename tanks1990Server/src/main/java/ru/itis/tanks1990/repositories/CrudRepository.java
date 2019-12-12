package ru.itis.tanks1990.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    boolean save(T t);
    void update(T t);
    Optional<T> find(ID id);
    boolean delete(T t);

    List<T> findAll();
}

