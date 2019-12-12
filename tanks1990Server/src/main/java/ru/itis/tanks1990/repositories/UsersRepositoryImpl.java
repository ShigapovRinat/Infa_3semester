package ru.itis.tanks1990.repositories;

import ru.itis.tanks1990.models.User;
import ru.itis.tanks1990.utils.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository{

    private Connection connection;

    public UsersRepositoryImpl(String propertyPath) {
        this.connection = new dbConnection(propertyPath).getConnection();
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.user (login, password) VALUES (?,?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            return statement.execute();
        } catch (SQLException e) {
            System.out.println("Error during adding user in db");
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }


    public Optional<User> find(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM public.user WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(mapper.mapRow(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println("Error during finding user");
            throw new IllegalArgumentException(e);
        }
    }


    private RowMapper<User> mapper = rs -> {
        try {
            return User.builder().id(rs.getLong("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .build();
        } catch (SQLException e) {
            System.out.println("User mapping error");
            throw new IllegalArgumentException(e);
        }
    };


    @Override
    public Optional<User> findByLogin(String login) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM public.user WHERE login = ?");
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(mapper.mapRow(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println("Error during finding user");
            throw new IllegalArgumentException(e);
        }
    }
}
