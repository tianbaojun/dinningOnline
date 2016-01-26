package com.tabjin.administrator.dinningonline.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.tabjin.administrator.dinningonline.R;
import com.tabjin.administrator.dinningonline.bean.Food;

import java.util.List;

/**
 * Created by Administrator on 2016/1/26.
 */
public class CartListAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    private List<Food> foods;
    private Context context;

    public CartListAdapter(Context context,List<Food> foods){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.foods = foods;
    }
    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //以下这些需设为局部变量，否则程序运行时会反应很慢，甚至textview不会发生改变
        Button add;
        final Button sub;
        final TextView tv_num;
        TextView tv_name;
        TextView tv_price;

        final Food food = foods.get(position);
        convertView =  mInflater.inflate(R.layout.cart_list_item,null);
        tv_num = (TextView) convertView.findViewById(R.id.num_food_list_cart);
        tv_name = (TextView) convertView.findViewById(R.id.name_food_list_cart);
        tv_price = (TextView) convertView.findViewById(R.id.price_food_list_cart);
        add = (Button) convertView.findViewById(R.id.numAdd_food_list_cart);
        sub = (Button) convertView.findViewById(R.id.numSub_food_list_cart);

        //给加按键设置监听，实现数量显示变化
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int foodNum = food.getNum();
                tv_num.setText(String.valueOf(++foodNum));
                food.setNum(foodNum);
//                notifyDataSetChanged();
            }
        });

        //给减按键设置监听，实现数量显示变化
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int foodNum = food.getNum();
                if(foodNum>0) {
                    tv_num.setText(String.valueOf(--foodNum));
                }
                food.setNum(foodNum);

            }
        });
        tv_name.setText(food.getName());
        tv_price.setText(String.valueOf(food.getPrice()));
        tv_num.setText(String.valueOf(food.getNum()));
        return convertView;
    }

}
