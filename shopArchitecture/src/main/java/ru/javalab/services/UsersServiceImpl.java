package ru.javalab.services;

import ru.javalab.contex.Component;
import ru.javalab.dto.GoodDto;
import ru.javalab.models.Good;
import ru.javalab.repositories.GoodRepository;
import ru.javalab.repositories.OrderHistoryRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService, Component {
    private GoodRepository goodRepository;
    private OrderHistoryRepository orderHistoryRepository;

    public UsersServiceImpl() {
    }

    public UsersServiceImpl(GoodRepository goodRepository, OrderHistoryRepository orderHistoryRepository) {
        this.goodRepository = goodRepository;
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public List<Good> listGood() {
        return goodRepository.findAll();
    }

    @Override
    public boolean buyGood(String login, String name) {
        if (orderHistoryRepository.createOrder(login, name)){
            return true;
        } else throw new IllegalArgumentException();
    }

    @Override
    public String getName() {
        return "usersService";
    }
}
