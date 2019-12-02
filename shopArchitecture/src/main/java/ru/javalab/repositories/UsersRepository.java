package ru.javalab.repositories;


import ru.javalab.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
