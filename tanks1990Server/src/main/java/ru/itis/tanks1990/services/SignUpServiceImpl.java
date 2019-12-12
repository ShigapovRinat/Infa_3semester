package ru.itis.tanks1990.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.tanks1990.models.User;
import ru.itis.tanks1990.repositories.UsersRepository;

import java.util.Optional;

public class SignUpServiceImpl implements SignUpService {
    private UsersRepository usersRepository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public SignUpServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User registration(String login, String password) {
        Optional<User> candidate = usersRepository.findByLogin(login);
        if (!candidate.isPresent()) {
            User user = User.builder().login(login).password(encoder.encode(password)).build();
            usersRepository.save(user);
            System.out.println("Registration is success");
            return user;
        }
        System.out.println("This login is already taken");
        return null;
    }
}
