package ru.javalab.repositories;


import ru.javalab.contex.Component;
import ru.javalab.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository, Component {
    private Connection connection;

    public UsersRepositoryImpl() {
        this.connection = new DBConnection("C:/Users/pc/Infa_3sem/shopSite/src/main/resources/db.properties").getConnection();

    }

    public UsersRepositoryImpl(String propertyPath) {
        this.connection = new DBConnection(propertyPath).getConnection();
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


    public Optional<User> find(Integer id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM public.user WHERE id = ?");
            stmt.setInt(1, id);
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
            return User.builder().id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .role(rs.getInt("role_id"))
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

    @Override
    public String getName() {
        return "usersRepository";
    }

//        public Integer findIdByUsername(String username) {
//        PreparedStatement statement;
//        try {
//            statement = connection.prepareStatement("SELECT id FROM public.user WHERE login = ?");
//            statement.setString(1, username);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("id");
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            throw new IllegalArgumentException(e);
//        }
//    }
}
