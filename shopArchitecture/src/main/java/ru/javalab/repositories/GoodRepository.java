package ru.javalab.repositories;

import ru.javalab.models.Good;
import java.util.Optional;

public interface GoodRepository extends CrudRepository<Good, Integer> {
    Optional<Good> findByName(String name);
}
