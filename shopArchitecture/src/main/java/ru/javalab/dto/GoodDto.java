package ru.javalab.dto;

import ru.javalab.models.Good;

public class GoodDto  implements Dto {
    private String name;
    private Integer price;

    public GoodDto() {
    }

    private GoodDto(Builder builder){
        this.name = builder.name;
        this.price = builder.price;
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
        private String name;
        private Integer price;

        public Builder price(Integer price) {
            this.price = price;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public GoodDto build(){
            return new GoodDto(this);
        }
    }

    public static GoodDto from(Good good){
        return GoodDto.builder().name(good.getName()).price(good.getPrice()).build();
    }
}
