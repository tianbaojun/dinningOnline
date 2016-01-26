package com.tabjin.administrator.dinningonline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.tabjin.administrator.dinningonline.bean.Cart;
import com.tabjin.administrator.dinningonline.bean.Food;
import com.tabjin.administrator.dinningonline.util.CartListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/26.
 */
public class CartActivity extends AppCompatActivity {

    private ListView cart_list;
    private TextView numFoodText;
    private TextView amountText;
    private ArrayList<Food> foods;
    private ArrayList<Map<String,Object>> foodArrayList;
    private Cart cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart_list = (ListView) findViewById(R.id.cart_list);
        amountText = (TextView) findViewById(R.id.amount_list_cart);
        numFoodText = (TextView) findViewById(R.id.num_food_list_cart);
        foodArrayList = new ArrayList<>();
        foods = new ArrayList<>();

        //初始化food列表
        for(int i=0;i<3;i++){
            foods.add(new Food("美食"+i, "这是美食"+i+"的简介.......", (int)(Math.random()*100), 1));
        }
        cart = new Cart(foods,0);
        for(Food food:foods){
            Map<String,Object> item = new HashMap<>();
            item.put("name",food.getName());
            item.put("price",food.getPrice());
            foodArrayList.add(item);
        }

        //如果要在listView当中进行操作，simpleadapter不能实现。需要用BaseAdapter给购物车中的列表设置adapter

        cart_list.setAdapter(new CartListAdapter(CartActivity.this,foods));
    }
}
