package ru.javalab.dispatcher;

import ru.javalab.dto.Dto;
import ru.javalab.dto.GoodDto;
import ru.javalab.dto.UserDto;
import ru.javalab.protocol.Request;
import ru.javalab.repositories.*;
import ru.javalab.services.*;
import ru.javalab.utils.TokenUtil;

public class RequestsDispatcher {

    private SignInService signInService;
    private SignUpService signUpService;
    private UsersService usersService;
    private AdminService adminService;
    private GoodRepository goodRepository;
    private UsersRepository usersRepository;
    private OrderHistoryRepository orderHistoryRepository;

    public RequestsDispatcher() {
        this.goodRepository = new GoodRepositoryImpl();
        this.usersRepository = new UsersRepositoryImpl();
        this.orderHistoryRepository = new OrderHistoryRepositoryImpl();
        this.signInService = new SignInServiceImpl(usersRepository);
        this.signUpService = new SignUpServiceImpl(usersRepository);
        this.usersService = new UsersServiceImpl(goodRepository, orderHistoryRepository);
        this.adminService = new AdminServiceImpl(goodRepository);


    }

    public Dto doDispatch(Request request) {

        TokenUtil tokenUtil = new TokenUtil();
        String token = request.getToken();

        if (request.getCommand().equals("login")) {
            try {
                UserDto user = signInService.signIn(request.getParameter("login"), request.getParameter("password"));
//                request.setToken(tokenUtil.getToken(user));
                user.setToken(tokenUtil.getToken(user));
                return user;
            } catch (IllegalArgumentException e) {
                System.out.println("You aren't login");
                return new UserDto();
            }
        }

        if (request.getCommand().equals("registration")) {
            try {
                UserDto user = signUpService.signUp(request.getParameter("login"), request.getParameter("password"));
//                request.setToken(tokenUtil.getToken(user));
                user.setToken(tokenUtil.getToken(user));
                return user;
            } catch (IllegalStateException e) {
                System.out.println("You aren't registration");
                return new UserDto();
            }
        }

        if (tokenUtil.verifyToken(token) != null) {
            String login = tokenUtil.getLogin(token);
            Boolean isAdmin = tokenUtil.isAdmin(token);

            if (request.getCommand().equals("buy")) {
                try {
                    String name = request.getParameter("name");
                    usersService.buyGood(login, name);
                    return GoodDto.from(goodRepository.findByName(name).get());
                } catch (IllegalArgumentException e) {
                    System.out.println("Такого товара не существует");
                    return new GoodDto();
                }
            }
            if (request.getCommand().equals("add")) {
                try {
                    if (isAdmin) {
                        return adminService.addGood(request.getParameter("name"),
                                Integer.parseInt(request.getParameter("price")));
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Такой товар уже существует");
                    return new GoodDto();
                }
            }

            if (request.getCommand().equals("delete")) {
                try {
                    if (isAdmin) {
                        return adminService.deleteGood(request.getParameter("name"));
                    }
                } catch (IllegalStateException e) {
                    System.out.println("Такого товара не существует");
                    return new GoodDto();
                }
            }
        }
        return null;
    }
}
