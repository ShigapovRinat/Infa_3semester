package ru.javalab.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javalab.contex.Component;
import ru.javalab.dto.UserDto;
import ru.javalab.models.User;
import ru.javalab.repositories.UsersRepository;

import java.util.Optional;

public class SignUpServiceImpl implements SignUpService, Component {
    private UsersRepository usersRepository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public SignUpServiceImpl() {
    }

    public SignUpServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserDto signUp(String login, String password) {
        Optional<User> candidate = usersRepository.findByLogin(login);
        if (!candidate.isPresent()) {
            User user = User.builder().login(login).password(encoder.encode(password)).build();
            usersRepository.save(user);
            System.out.println("Registration is success");
            return UserDto.from(user);
        } else throw new IllegalArgumentException();
    }

    @Override
    public String getName() {
        return "signUpService";
    }
}
