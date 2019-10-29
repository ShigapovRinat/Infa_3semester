package repositories;

import models.Message;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
