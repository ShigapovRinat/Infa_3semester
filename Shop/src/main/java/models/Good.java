package models;

public class Good extends Command{
    private int id;
    private String name;
    private int price;

    public Good() {
    }

    public Good(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
