package ru.javalab.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.javalab.protocol.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;


    public Server(String dbPropFilePath) {
        clients = new ArrayList<>();
    }


    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
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
        private Socket clientSocket;
        private BufferedReader reader;
        PrintWriter writer;
        private ObjectMapper mapper = new ObjectMapper();
        private Request request = new Request();
        private RequestsHandler requestsHandler;
        private Response response;
        private Map<String, String> payload;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            this.requestsHandler = new RequestsHandler();
            System.out.println("New client!");
        }

        @Override
        public void run() {
            try {
                reader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
//                writer = new PrintWriter(clientSocket.getOutputStream(), true);
                String line;
                while ((line = reader.readLine()) != null) {
                    ObjectNode json = (ObjectNode) mapper.readTree(line);
                    request.setCommand(json.get("command").asText());
                    request.setToken(json.get("token").asText());

                    payload = new HashMap<>();
                    Iterator iterator = json.get("payload").fieldNames();
                    String field;
                    while (iterator.hasNext()) {
                        field = (String) iterator.next();
                        payload.put(field, json.get("payload").get(field).asText());
                    }
                    request.setPayload(payload);
                    response = requestsHandler.handleRequest(request);
                    sendMessage(Response.getJson(response));

                }
                clientSocket.close();
                reader.close();
                writer.close();
            } catch (IOException e) {
                System.out.println("Клиент вышел");
            }
        }

        private void sendMessage(String string) {
            PrintWriter out = null;
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(string);
            } catch (IOException e) {
                System.out.println("Ошибка соединения");
                e.printStackTrace();
            }

        }
    }
}


//switch (json.get("header").asText()) {
//                        case "login":
//                            Login login = new Login();
//                            login.setLogin(json.get("payload").get("login").asText());
//                            login.setPassword(json.get("payload").get("password").asText());
//                            authorization(login);
//                            break;
//                        case "logout":
//                            clients.remove(this);
//                            token = null;
////                            user = null;
//                            sendMessage("SERVER: You are logout");
//                            break;
//                        case "message":
//                            try {
////                                if (user.getLogin() != null) {
//                                if (AuthService.verifyToken(token) != null) {
//                                    for (ClientHandler client : clients) {
//                                        if (client.clientSocket.isClosed()) {
//                                            clients.remove(client);
//                                            continue;
//                                        }
//                                        PrintWriter writerMessage = new PrintWriter(client.clientSocket.getOutputStream(), true);
//                                        String text = json.get("payload").get("text").asText();
//                                        writerMessage.println(AuthService.getLogin(token) + ": " + text);
//                                        writeInDB(text);
//                                    }
//                                } else {
//                                    sendMessage("You aren't authorization");
//                                }
//                            } catch (NullPointerException e) {
//                                sendMessage("You aren't authorization");
//                            }
//                            break;
//                        case "registration":
//                            Registration registration = new Registration();
//                            registration.setLogin(json.get("payload").get("login").asText());
//                            registration.setPassword(json.get("payload").get("password").asText());
//                            registration.setRole(json.get("payload").get("role").asInt());
//                            registration(registration);
//                            break;
//                        case "command":
//                            if (token != null) {
//                                if (AuthService.verifyToken(token) != null) {
//                                    writer = new PrintWriter(clientSocket.getOutputStream(), true);
//                                    switch (json.get("payload").get("command").asText()) {
//                                        case "get_messages":
//                                            List<Message> messages = messageRepository.pagination(json.get("payload").get("page").asInt(), json.get("payload").get("size").asInt());
//                                            String data = mapper.writeValueAsString(messages);
//                                            writer.println(data);
//                                            break;
//                                        case "add_good":
//                                            if (AuthService.isAdmin(token)) {
//                                                Good good = new Good();
//                                                good.setName(json.get("payload").get("name").asText());
//                                                good.setPrice(json.get("payload").get("price").asInt());
//                                                goodRepository.save(good);
//                                                writer.println("Success add");
//                                            } else {
//                                                writer.println("Only admin can add good");
//                                            }
//                                            break;
//                                        case "delete_good":
//                                            if (AuthService.isAdmin(token)) {
//                                                Good good = new Good();
//                                                good.setName(json.get("payload").get("name").asText());
//                                                goodRepository.delete(good);
//                                                writer.println("Success delete");
//                                            } else {
//                                                writer.println("Only admin can delete good");
//                                            }
//                                            break;
//                                        case "list_good":
//                                            List<Good> goods = goodRepository.findAll();
//                                            String g = mapper.writeValueAsString(goods);
//                                            writer.println(g);
//                                            break;
//                                        default:
//                                            sendMessage("SERVER: Didn't found this command");
//                                    }
//                                } else sendMessage("You aren't authorization");
//                            } else sendMessage("You aren't authorization");
//                            break;
//                    }
