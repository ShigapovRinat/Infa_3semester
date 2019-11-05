package repositories;

import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements CrudRepository<User> {
    private Connection connection;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserRepositoryImpl(String propertyPath) {
        this.connection = new DBConnection(propertyPath).getConnection();
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.user (login, password) VALUES (?,?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, encoder.encode(user.getPassword()));
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
    public void delete(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    public Optional<User> find(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM public.user WHERE login = ? AND password = ?");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                encoder.matches(user.getPassword(), rs.getString("password"));
                return Optional.ofNullable(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println("Error during finding user");
            throw new IllegalArgumentException(e);
        }
    }

    public Optional<User> findForRegistration(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM public.user WHERE login = ?");
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println("Error during finding user");
            throw new IllegalArgumentException(e);
        }
    }

    public Integer findIdByUsername(String username) {
        PreparedStatement statement;
        try{
            statement = connection.prepareStatement("SELECT id FROM public.user WHERE login = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return  rs.getInt("id");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    //    private RowMapper<User> mapper = rs -> {
//        try {
//            return new User(rs.getString("login"),
//                    rs.getString("password"));
//        } catch (SQLException e) {
//            System.out.println("User mapping error");
//            throw new IllegalArgumentException(e);
//        }
//    };


}
