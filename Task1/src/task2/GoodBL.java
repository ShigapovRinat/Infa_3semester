package task2;

import java.sql.SQLException;
import java.util.List;

public class GoodBL {
    private DAO dao;

    {
        try {
            dao = new DAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGood(String name, int price) {
        try {
            dao.addGood(name, price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Good> getGoods() throws SQLException {
        return dao.getGoods();
    }

    public void removeGood(int index) {
        try {
            dao.removeGood(index);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTheMostExpensivePrice() {
        int price = 0;
        String name = "";
        try {
            for (Good g : dao.getGoods()) {
                if (price < g.getPrice()) {
                    price = g.getPrice();
                    name = g.getName();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}
