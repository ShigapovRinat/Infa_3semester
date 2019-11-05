package repositories;

import models.Message;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageRepositoryImpl implements CrudRepository<Message> {
    private Connection connection;

    public MessageRepositoryImpl(String propertiesPath) {
        this.connection = new DBConnection(propertiesPath).getConnection();
        userRepository = new UserRepositoryImpl(propertiesPath);
    }

    UserRepositoryImpl userRepository;

    @Override
    public boolean save(Message message) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.message(id_user, text) VALUES (?,?)");
            statement.setInt(1, userRepository.findIdByUsername(message.getUser().getUsername()));
            statement.setString(2, message.getText());
            return statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public Optional<Message> find(User user) {
        return Optional.empty();
    }

    @Override
    public void delete(Message message) {

    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    public ArrayList<Message> pagination(int page, int size){
        try {
            ArrayList<Message> messages = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.message ORDER BY date LIMIT ? OFFSET ?");
            statement.setInt(1, size);
            statement.setInt(2, size*(page-1)+1);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Message message = new Message(rs.getInt("id_message"), rs.getString("text"));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

//    private RowMapper<Message> mapper = rs -> {
//        try {
//            return new Message(rs.getInt("id_message"),
//                    rs.getString("text"));
//        } catch (SQLException e) {
//            System.out.println("User mapping error");
//            throw new IllegalArgumentException(e);
//        }
//    };

    private User findUserById(int id){
        User user = new User();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.user WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
