package ru.javalab.server;

import com.beust.jcommander.JCommander;

public class ServerStart {

    public static void main(String[] argv) {

        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        Server multiClientServer =
                new Server(args.getPropertiesPath());
        multiClientServer.start(args.getPort());


    }

}
