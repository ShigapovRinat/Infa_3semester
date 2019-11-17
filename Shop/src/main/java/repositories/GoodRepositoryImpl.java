package repositories;

import models.Good;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GoodRepositoryImpl implements CrudRepository<Good> {
    private Connection connection;

    public GoodRepositoryImpl(String propertiesPath) {
        this.connection = new DBConnection(propertiesPath).getConnection();
    }

    private RowMapper<Good> mapper = rs -> {
        try {
            return new Good(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price"));
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
    public Optional<Good> find(Good good) {
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
            e.printStackTrace();
            return null;
        }
    }
}
