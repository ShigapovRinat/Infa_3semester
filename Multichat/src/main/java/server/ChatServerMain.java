package server;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import repositories.DBConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
