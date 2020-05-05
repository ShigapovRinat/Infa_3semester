package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.MessageRepositoryImpl;
import repositories.UserRepositoryImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatServer {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;
    MessageRepositoryImpl messageRepository;
    UserRepositoryImpl userRepository;

    public ChatServer(String dbPropFilePath) {
        this.messageRepository = new MessageRepositoryImpl(dbPropFilePath);
        this.userRepository = new UserRepositoryImpl(dbPropFilePath);
        clients = new ArrayList<>();
    }


    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port, 2);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        while (true) {
            try {
                ClientHandler handler =
                        new ClientHandler(serverSocket.accept());
                handler.start();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }


    private class ClientHandler extends Thread {
        private User user;
        private Socket clientSocket;
        private BufferedReader reader;
        private ObjectMapper mapper = new ObjectMapper();
        private PasswordEncoder encoder = new BCryptPasswordEncoder();


        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            System.out.println("New client!");
        }

        @Override
        public void run() {
            try {
                reader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    ObjectNode json = (ObjectNode) mapper.readTree(line);
                    switch (json.get("header").asText()) {
                        case "login":
                            Login login = new Login();
                            login.setLogin(json.get("payload").get("login").asText());
                            login.setPassword(json.get("payload").get("password").asText());
                            authorization(login);
                            break;
                        case "logout":
                            clients.remove(this);
                            user = null;
                            break;
                        case "command":
                            PrintWriter writerCommand = new PrintWriter(clientSocket.getOutputStream(), true);
                            ArrayList<Message> messages = messageRepository.pagination(json.get("payload").get("page").asInt(), json.get("payload").get("size").asInt());
                            String data = mapper.writeValueAsString(messages);
                            writerCommand.println(data);
//                            writerCommand.close();
                            break;
                        case "message":
                            try {
                                if (user.getUsername() != null) {
                                    for (ClientHandler client : clients) {
                                        if (client.clientSocket.isClosed()) {
                                            clients.remove(client);
                                            continue;
                                        }
                                        PrintWriter writerMessage = new PrintWriter(client.clientSocket.getOutputStream(), true);
                                        String text = json.get("payload").get("text").asText();
                                        writerMessage.println(user.getUsername() + ": " + text);
                                        writeInDB(text);
//                                        writerMessage.close();
                                    }
                                } else {
                                    sendMessage("You aren't authorization");
                                }
                            } catch (NullPointerException e) {
                                sendMessage("You aren't authorization");
                            }
                            break;
                        case "registration":
                            Registration registration = new Registration();
                            registration.setLogin(json.get("payload").get("login").asText());
                            registration.setPassword(json.get("payload").get("password").asText());
                            registration(registration);
                            break;
                    }

                }
                clientSocket.close();
                reader.close();
            } catch (IOException e) {
                System.out.println("The user go out");
                throw new IllegalStateException(e);
            }

        }


        private boolean authorization(Login log) throws IOException {
            String login = log.getLogin();
            String password = log.getPassword();
            User user1 = new User(login, password);
            Optional<User> dbUser = userRepository.find(user1);
            if (dbUser.isPresent() && encoder.matches(password, dbUser.get().getPassword())) {
                this.user = dbUser.get();
                clients.add(this);
                sendMessage("SERVER: You are logged in");
                return true;
            } else {
                sendMessage("SERVER: Wrong login or password");
                return false;
            }
        }


        private boolean registration(Registration registration) throws IOException {
            String login = registration.getLogin();
            String password = registration.getPassword();
            User user = new User(login, encoder.encode(password));
            if (!userRepository.find(user).isPresent()) {
                clients.add(this);
                this.user = user;
                userRepository.save(user);
                sendMessage("SERVER: Registration is success");
                return true;
            } else {
                sendMessage("SERVER: This login is already taken");
                return false;
            }
        }

        private void sendMessage(String string) throws IOException {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(string);
        }

        private void writeInDB(String message) {
            Message currentMessage = new Message(user, message);
            messageRepository.save(currentMessage);
        }

    }

}
