package ru.itis.tanks1990.services;

import ru.itis.tanks1990.models.User;

public interface SignInService {
    User login(String login, String password);
}
