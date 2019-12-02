package ru.javalab.services;

import ru.javalab.dto.UserDto;

public interface SignUpService {
    UserDto signUp(String login, String password);
}
