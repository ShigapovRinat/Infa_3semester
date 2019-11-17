package server;

import models.Good;
import repositories.GoodRepositoryImpl;

public class AdminService {
    public AdminService(GoodRepositoryImpl goodRepository) {
        this.goodRepository = goodRepository;
    }

    private GoodRepositoryImpl goodRepository;

    public boolean deleteGood(int id){
        Good good = new Good();
        good.setId(id);
        return goodRepository.delete(good);
    }

    public boolean addGood(String name, int price){
        Good good = new Good();
        good.setName(name);
        good.setPrice(price);
        return goodRepository.save(good);
    }

}
