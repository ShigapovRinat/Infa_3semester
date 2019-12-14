package ru.javalab.repositories;

import ru.javalab.contex.Component;
import ru.javalab.models.Good;
import ru.javalab.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderHistoryRepositoryImpl implements OrderHistoryRepository, Component {
    private Connection connection;
    private UsersRepository usersRepository;
    private GoodRepository goodRepository;

    public OrderHistoryRepositoryImpl() {
        this.connection = new DBConnection("C:/Users/pc/Infa_3sem/shopSite/src/main/resources/db.properties")
                                            .getConnection();
    }

    public OrderHistoryRepositoryImpl(String propertiesPath) {
        this.connection = new DBConnection(propertiesPath).getConnection();
        this.usersRepository = new UsersRepositoryImpl(propertiesPath);
        this.goodRepository = new GoodRepositoryImpl(propertiesPath);
    }

    @Override
    public boolean createOrder(String loginUser, String nameGood) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO public.user_good(user_id, good_id) VALUES (?, ?)");
            User user = usersRepository.findByLogin(loginUser).orElseThrow(IllegalArgumentException::new);
            Good good = goodRepository.findByName(nameGood).orElseThrow(IllegalArgumentException::new);
            stmt.setInt(1, user.getId());
            stmt.setInt(2, good.getId());
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getName() {
        return "orderHistoryRepository";
    }
}
