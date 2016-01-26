package com.tabjin.administrator.dinningonline;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/25.
 */
public class AllfoodItemsActivity extends TabActivity{

    private List<Map<String,Object>> allfoodItemsList,allfoodItemsComments;
    private TabHost tabHost;
    public SharedPreferences itemType;
    public Context itemTypeContext;
    public ListView allfood_items_list;
    public ListView allfood_items_comments;
    private ArrayList<String> itemName,itemDesc,userName,userComment;
    private ArrayList<Integer> itemAttention;
    private String itemTypestr;

    public SimpleAdapter allfood_items_list_adapter;
    public SimpleAdapter allfood_items_comment_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allfood_items);
        //给allfoodItemsList菜品初始化
        allfoodItemsList = new ArrayList<>();
        itemName = new ArrayList<>();
        ArrayList<Double> itemPrice = new ArrayList<>();
        itemDesc = new ArrayList<>();
        itemAttention = new ArrayList<>();
        //给allfoodItemsComments评论初始化
        allfoodItemsComments = new ArrayList<>();
        userName = new ArrayList<>();
        userComment = new ArrayList<>();
        RelativeLayout toCart = (RelativeLayout) findViewById(R.id.from_food_items_to_cart);
        toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllfoodItemsActivity.this,CartActivity.class));
            }
        });
        //给菜品赋值
        for(int i =0;i<100;i++){
            Map<String,Object> item = new HashMap<>();
            itemName.add("菜品"+(i+1));
            itemAttention.add((int)(Math.random()*1000));
            itemDesc.add(itemName.get(i)+"的简介........");
            itemPrice.add((int) (Math.random() * 100) + 0.9);
            item.put("name", itemName.get(i));
            item.put("desc",itemDesc.get(i));
            item.put("attention", "关注度："+itemAttention.get(i));
            item.put("price","￥"+ itemPrice.get(i));
            allfoodItemsList.add(item);
        }
        //给评论赋值
        for(int i=0;i<30;i++){
            Map<String,Object> comment = new HashMap<>();
            userName.add("user"+(i+1));
            userComment.add(userName.get(i)+"的评论.........");
            comment.put("name", userName.get(i));
            comment.put("comment",userComment.get(i));
            allfoodItemsComments.add(comment);
        }

        //取到菜品的值
        try {
            itemTypeContext = createPackageContext("com.tabjin.administrator.dinningonline",MODE_PRIVATE);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        itemType = itemTypeContext.getSharedPreferences("dinning", MODE_PRIVATE);
        itemTypestr = itemType.getString("itemTypeName","");
        tabHost = getTabHost();

        TabHost.TabSpec allfood_items_tab = tabHost.newTabSpec("allfood_items_tab")
                .setIndicator(itemType.getString("itemTypeName",""))
                .setContent(R.id.tab01);
        tabHost.addTab(allfood_items_tab);

        TabHost.TabSpec allfood_items_comments_tab = tabHost.newTabSpec("allfood_items_comments_tab")
                .setIndicator("评论")
                .setContent(R.id.tab02);
        tabHost.addTab(allfood_items_comments_tab);

        //allfood_items_list的adapter设置
        allfood_items_list_adapter = new SimpleAdapter(this,allfoodItemsList,
                R.layout.allfood_items_list,
                new String[]{"name","desc","attention","price"},
                new int[]{
                        R.id.allfood_items_list_name,
                        R.id.allfood_items_list_desc,
                        R.id.allfood_items_list_attention,
                        R.id.allfood_items_list_price});
        allfood_items_list = (ListView) findViewById(R.id.allfood_items_list);
        allfood_items_list.setAdapter(allfood_items_list_adapter);
        //allfoodItemsComments的adapter设置
        allfood_items_comment_adapter = new SimpleAdapter(this,allfoodItemsComments,
                R.layout.allfood_items_comment,
                new String[]{"name","comment"},
                new int[]{
                        R.id.allfood_items_comment_username,
                        R.id.allfood_items_comment_userdesc});
        allfood_items_comments = (ListView) findViewById(R.id.allfood_items_comments);
        allfood_items_comments.setAdapter(allfood_items_comment_adapter);
    }


}
