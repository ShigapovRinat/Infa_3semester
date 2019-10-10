package task2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    //    private List<Good> goods = new ArrayList<>();
//    private Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/good", "root", "password");
//    private Statement statement = connection.createStatement();
    String sql;
    ResultSet rs;

    public DAO() throws SQLException {
    }

    public void addGood(String name, int price) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/good?serverTimezone=Europe/Moscow", "root", "password");
        Statement statement = connection.createStatement();
        sql = "INSERT INTO good(name, price) " +
                "VALUES ('" + name + "','" + price + "')";
        statement.executeUpdate(sql);
//        goods.add(new Good(goods.size() + 1, name, price));
    }

    public List<Good> getGoods() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/good?serverTimezone=Europe/Moscow", "root", "password");
        Statement statement = connection.createStatement();
        List<Good> goods = new ArrayList<>();
        sql = "select * from good";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            Good g = new Good(rs.getInt("id"), rs.getString("name"), rs.getInt("price"));
            goods.add(g);
        }
        return goods;
    }

    public void removeGood(int index) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/good?serverTimezone=Europe/Moscow", "root", "password");
        Statement statement = connection.createStatement();
        sql = "DELETE FROM good WHERE id = '" +
                index +
                "'";
        statement.executeUpdate(sql);
//        goods.remove(index - 1);
//        for (int i = index - 1; i < goods.size(); i++) {
//            goods.get(i).setId(goods.get(i).getId() - 1);
//        }
    }

}
