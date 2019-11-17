package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;

import java.io.*;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientWithJson {
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private JsonMessage json = new JsonMessage();
    private ObjectMapper mapper = new ObjectMapper();

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            new Thread(receiveMessagesTask).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
            try {
                if (message != null) {
                    String[] args = message.split(" ");
                    json.setHeader(args[0]);
                    switch (args[0]) {
                        case "login":
                            Login login = new Login();
                            login.setLogin(args[1]);
                            login.setPassword(args[2]);
                            json.setPayload(login);
//                            System.out.println(mapper.writeValueAsString(json));
                            writer.println(mapper.writeValueAsString(json));
                            break;
                        case "logout":
                            Logout logout = new Logout();
                            logout.setLog(false);
                            json.setPayload(logout);
                            writer.println(mapper.writeValueAsString(json));
                            break;
                        case "message":
                            Message mes = new Message();
                            String text = "";
                            for (int i = 1; i < args.length; i++) {
                                text += args[i] + " ";
                            }
                            mes.setText(text);
                            json.setPayload(mes);
                            writer.println(mapper.writeValueAsString(json));
                            break;
                        case "command":
                            Command command = new Command();
                            command.setPage(Integer.parseInt(args[1]));
                            command.setSize(Integer.parseInt(args[2]));
                            json.setPayload(command);
                            writer.println(mapper.writeValueAsString(json));
                            break;
                        case "registration":
                            Registration registration = new Registration();
                            registration.setLogin(args[1]);
                            registration.setPassword(args[2]);
                            json.setPayload(registration);
//                            System.out.println(mapper.writeValueAsString(json));
                            writer.println(mapper.writeValueAsString(json));
                            break;
                        default:
                            System.out.println("Did not find this command");
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
    }

//    public void sendInformation(String message) {
//        writer.println(message);
//    }

    private Runnable receiveMessagesTask = new Runnable() {
        public void run() {
            while (true) {
                try {
                    String message = reader.readLine();
                    if(message != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    };

}