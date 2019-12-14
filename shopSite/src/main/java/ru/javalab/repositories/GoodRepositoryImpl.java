package ru.javalab.repositories;

import ru.javalab.contex.Component;
import ru.javalab.models.Good;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GoodRepositoryImpl implements GoodRepository, Component {
    private Connection connection;

    public GoodRepositoryImpl() {
        this.connection = new DBConnection("C:/Users/pc/Infa_3sem/shopSite/src/main/resources/db.properties")
                                            .getConnection();
    }

    public GoodRepositoryImpl(String propertiesPath) {
        this.connection = new DBConnection(propertiesPath).getConnection();
    }

    private RowMapper<Good> mapper = rs -> {
        try {
            return Good.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .price(rs.getInt("price"))
                    .build();
        } catch (SQLException e) {
            System.out.println("Good mapping error");
            throw new IllegalArgumentException(e);
        }
    };

    @Override
    public boolean save(Good good) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.good(name, price) VALUES (?,?)");
            statement.setString(1, good.getName());
            statement.setInt(2, good.getPrice());
            return statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Good good) {

    }

    @Override
    public Optional<Good> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Good good) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM public.good WHERE name=?");
            statement.setString(1, good.getName());
            return statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Good> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(("SELECT * FROM good"));
            List<Good> goods = new ArrayList<>();
            while (rs.next()){
               goods.add(mapper.mapRow(rs));
            }
            return goods;
        } catch (SQLException e) {
            System.out.println("Error during finding goods");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Good> findByName(String name) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM public.good WHERE name = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(mapper.mapRow(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println("Error during finding good");
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getName() {
        return "goodRepository";
    }
}
