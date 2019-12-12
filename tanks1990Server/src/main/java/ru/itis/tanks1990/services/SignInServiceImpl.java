package ru.itis.tanks1990.services;

import ru.itis.tanks1990.models.User;
import ru.itis.tanks1990.repositories.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {
    private UsersRepository usersRepository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public SignInServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

//    public User login(String login, String password) {
//        Optional<User> dbUser = usersRepository.findByLogin(login);
//        if (dbUser.isPresent() && encoder.matches(password, dbUser.get().getPassword())) {
//            token = AuthService.getToken(dbUser.get());
//            clients.add(this);
//            sendMessage("SERVER: You are logged in");
//            return true;
//        } else {
//            sendMessage("SERVER: Wrong login or password");
//            return false;
//        }
//    }

    @Override
    public User login(String login, String password) {
        Optional<User> userCandidate = usersRepository.findByLogin(login);
        if(userCandidate.isPresent()) {
            User user = userCandidate.get();
            if(encoder.matches(password, user.getPassword())){
                System.out.println("пароль верный");
                return user;
            } System.out.println("пароль не верный");
        }
        System.out.println("Пользователя с данным логином не существует");
        return null;
    }
}
