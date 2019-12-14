package ru.javalab.services;

import ru.javalab.contex.Component;
import ru.javalab.dto.GoodDto;
import ru.javalab.models.Good;
import ru.javalab.repositories.GoodRepository;

public class AdminServiceImpl implements  AdminService, Component {

    private GoodRepository goodRepository;

    public AdminServiceImpl() {
    }

    public AdminServiceImpl(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    public GoodDto deleteGood(String name){
        Good good = goodRepository.findByName(name).get();
        if (goodRepository.delete(good)){
            return GoodDto.from(good);
        } else throw new IllegalArgumentException();
    }

    public GoodDto addGood(String name, Integer price){
        Good good = new Good();
        good.setName(name);
        good.setPrice(price);
        if (goodRepository.save(good)){
            return GoodDto.from(good);
        } else throw new IllegalArgumentException();
    }

    @Override
    public String getName() {
        return "adminService";
    }
}
