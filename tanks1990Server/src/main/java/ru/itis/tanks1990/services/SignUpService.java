package ru.itis.tanks1990.services;

import ru.itis.tanks1990.models.User;

public interface SignUpService {
    User registration(String login, String password);
}
