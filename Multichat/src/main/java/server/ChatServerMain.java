package server;

import com.beust.jcommander.JCommander;

public class ChatServerMain {

    public static void main(String[] argv) {

        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        ChatServer multiClientServer =
                new ChatServer(args.getPropertiesPath());
        multiClientServer.start(args.getPort());


    }

}
