package client;

import com.beust.jcommander.JCommander;

import java.util.Scanner;

public class ChatClientMain {
    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        ChatClient chatClient = new ChatClient();
        chatClient.startConnection(args.getIp(), args.getPort());
        Scanner sc = new Scanner(System.in);
        while (true) {
            String message = sc.nextLine();
            chatClient.sendMessage(message);
        }
    }
}