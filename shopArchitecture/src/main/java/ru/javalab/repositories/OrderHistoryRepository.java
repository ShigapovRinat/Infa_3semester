package ru.javalab.repositories;

public interface OrderHistoryRepository {
    boolean createOrder(String loginUser, String titleGood);
}
