import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties property = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(args[1]);
            property.load(fis);
            String dbUrl = property.getProperty("url");
            String dbUser = property.getProperty("user");
            String dbPassword = property.getProperty("password");
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        int port = Integer.parseInt(args[0]);
        Server multiClientServer =
                new Server();
        multiClientServer.start(port);


    }

}
