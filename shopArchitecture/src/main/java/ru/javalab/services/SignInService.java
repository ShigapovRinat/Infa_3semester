package ru.javalab.services;

import ru.javalab.dto.UserDto;

public interface SignInService {
    UserDto signIn(String login, String password);
}
