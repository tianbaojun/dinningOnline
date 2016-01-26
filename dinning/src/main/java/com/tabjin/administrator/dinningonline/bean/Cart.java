package com.tabjin.administrator.dinningonline.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/26.
 */
public class Cart {

    private ArrayList<Food> food;
    private int amount;

    public Cart(ArrayList<Food> food, int amount) {
        this.food = food;
        this.amount = amount;
    }

    public ArrayList<Food> getFood() {
        return food;
    }

    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "food=" + food +
                ", amount=" + amount +
                '}';
    }
}
