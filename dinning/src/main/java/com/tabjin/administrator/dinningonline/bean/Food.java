package com.tabjin.administrator.dinningonline.bean;

/**
 * Created by Administrator on 2016/1/26.
 */
public class Food {

    private String name;
    private String desc;
    private double price;
    private int num;

    public Food(){}

    public Food(String name, String desc, double price, int num) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
