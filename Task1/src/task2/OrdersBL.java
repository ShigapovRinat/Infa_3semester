package task2;

import java.util.ArrayList;
import java.util.List;

public class OrdersBL {
    private List<Good> goods = new ArrayList<>();

    public void addGood(String name){
        goods.add(new Good(goods.size()+1, name));
    }

    public List<Good> getGoods(){
        return goods;
    }

    public void removeGood(int index){
        goods.remove(index-1);
        for (int i = index-1; i < goods.size(); i++) {
            goods.get(i).setId(goods.get(i).getId() - 1);
        }
    }
}
