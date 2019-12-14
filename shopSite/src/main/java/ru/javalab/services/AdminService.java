package ru.javalab.services;

import ru.javalab.dto.GoodDto;

public interface AdminService {
    GoodDto deleteGood(String name);
    GoodDto addGood(String name, Integer price);
}
