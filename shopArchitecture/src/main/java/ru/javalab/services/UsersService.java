package ru.javalab.services;

import ru.javalab.dto.GoodDto;
import ru.javalab.models.Good;

import java.util.List;

public interface UsersService {
    List<Good> listGood();
    boolean buyGood(String login, String nameGood);
}
