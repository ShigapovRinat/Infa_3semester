package ru.javalab.models;

public class Good {
    private Integer id;
    private String name;
    private Integer price;

    public Good() {
    }

    private Good(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
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

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Integer id;
        private String name;
        private Integer price;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder price(Integer price) {
            this.price = price;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Good build(){
            return new Good(this);
        }
    }
}
