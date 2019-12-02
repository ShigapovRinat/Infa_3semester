package ru.javalab.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javalab.contex.Component;
import ru.javalab.dto.UserDto;
import ru.javalab.models.User;
import ru.javalab.repositories.UsersRepository;
import java.util.Optional;
import static ru.javalab.dto.UserDto.from;

public class SignInServiceImpl implements SignInService, Component {
    private UsersRepository usersRepository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public SignInServiceImpl() {
    }

    public SignInServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDto signIn(String login, String password) {
        System.out.println(login);
        Optional<User> userCandidate = usersRepository.findByLogin(login);
        if(userCandidate.isPresent()) {
            User user = userCandidate.get();
            if(encoder.matches(password, user.getPassword())){
                System.out.println("пароль верный");
                return from(user);
            } System.out.println("пароль не верный");
        } throw new IllegalArgumentException();
    }

    @Override
    public String getName() {
        return "signInService";
    }
}
