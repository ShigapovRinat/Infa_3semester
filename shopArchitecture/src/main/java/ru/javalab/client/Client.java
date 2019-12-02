package ru.javalab.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javalab.protocol.Request;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private ObjectMapper mapper = new ObjectMapper();
    private Request request;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            new Thread(receiveMessagesTask).start();
        } catch (IOException e) {
            System.out.println("Сервер не найден");
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        try {
            if (message != null) {
                request = new Request();
                String[] args = message.split(" ");
                request.setCommand(args[0]);
                switch (args[0]) {
                    case "login":
                        request.setPayload("login", args[1]);
                        request.setPayload("password", args[2]);
                        System.out.println(mapper.writeValueAsString(request));
                        writer.println(mapper.writeValueAsString(request));
                        break;
                    case "add":
                        request.setPayload("name", args[1]);
                        request.setPayload("price", args[2]);
                        writer.println(mapper.writeValueAsString(request));
                        break;
                    case "delete":
                        request.setPayload("name", args[1]);
                        writer.println(mapper.writeValueAsString(request));
                        break;
                    case "list_good":
                        writer.println(mapper.writeValueAsString(request));
                        break;
                    case "registration":
                        request.setPayload("login", args[1]);
                        request.setPayload("password", args[2]);
                        writer.println(mapper.writeValueAsString(request));
                        break;
                    default:
                        System.out.println("Did not find this command");
                }
            }
        } catch (IOException e) {
            System.out.println("Потеряна связь с сервером");
            throw new IllegalStateException(e);
        }
    }

    private Runnable receiveMessagesTask = new Runnable() {
        public void run() {
            while (true) {
                try {
                    String message = reader.readLine();
                    if (message != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Потеряна связь с сервером");
                }
            }
        }
    };


}