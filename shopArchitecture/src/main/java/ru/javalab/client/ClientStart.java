package ru.javalab.client;

import com.beust.jcommander.JCommander;

import java.util.Scanner;

public class ClientStart {
    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        Client client = new Client();
        client.startConnection(args.getIp(), args.getPort());
        Scanner sc = new Scanner(System.in);
        while (true) {
            String message = sc.nextLine();
            client.sendMessage(message);
        }
    }
}