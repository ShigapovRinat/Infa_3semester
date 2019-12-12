package ru.itis.tanks1990.repositories;

import ru.itis.tanks1990.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long>{
    Optional<User> findByLogin(String login);
}
