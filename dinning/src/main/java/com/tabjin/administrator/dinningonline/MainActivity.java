package com.tabjin.administrator.dinningonline;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.tabjin.administrator.dinningonline.bean.Food;
import com.tabjin.administrator.dinningonline.bean.Order;
import com.tabjin.administrator.dinningonline.util.OrderListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/1/23.
 */
public class MainActivity extends TabActivity  implements GestureDetector.OnGestureListener,View.OnClickListener {

    //首页
    private ViewFlipper viewFlipper;
    private GestureDetector gestureDetector;
    final int FLIP_DISTANCE = 100;
//    订单列表
    ListView trading_order_list;
    ListView history_order_list;
    private List<Order> orders;
    private List<Order> historyOrders;
    private List<Order> tradingOrders;
    private Button bt_trading,bt_history;
//    我的
    private ImageView image_myinfo;
    private TextView   login_tv_myinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = getTabHost();
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1")
                .setIndicator("首页")
                .setContent(R.id.tab01);
        tabHost.addTab(tab1);
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2")
                .setIndicator("订单")
                .setContent(R.id.tab02);
        tabHost.addTab(tab2);
        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3")
                .setIndicator("我的")
                .setContent(R.id.tab03);
        tabHost.addTab(tab3);

        //首页
        viewFlipper = (ViewFlipper) findViewById(R.id.vf_homepages);
        gestureDetector = new GestureDetector(this,this);
        auto(viewFlipper);

        //订单列表
        historyOrders = new ArrayList<>();
        tradingOrders = new ArrayList<>();
        orders = ordersInit(6);
        historyOrder(orders);
        trading_order_list = (ListView) findViewById(R.id.list_order_trading);
        history_order_list = (ListView) findViewById(R.id.list_order_history);
        bt_trading = (Button) findViewById(R.id.bt_trading);
        bt_history = (Button) findViewById(R.id.bt_history);
        bt_trading.setOnClickListener(this);
        bt_history.setOnClickListener(this);
        trading_order_list.setAdapter(new OrderListAdapter(tradingOrders, this));
        history_order_list.setAdapter(new OrderListAdapter(historyOrders, this));
        trading_order_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TradingOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", tradingOrders.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        history_order_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TradingOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", historyOrders.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //我的相关
        image_myinfo = (ImageView) findViewById(R.id.my_image);
        login_tv_myinfo = (TextView) findViewById(R.id.login_tv_myinfo);
        image_myinfo.setOnClickListener(this);
        login_tv_myinfo.setOnClickListener(this);

    }

    //首页的相关设置
    public void allfood(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, AllfoodActivity.class);
        this.startActivity(intent);
    }

    /**
     * 图片自动滑动
     * @param view
     */
    public void auto(View view){
        viewFlipper.setInAnimation(this, R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, R.anim.slide_out_right);
        viewFlipper.startFlipping();
    }

    /**
     * 设置手势滑动效果
     * @param e1/
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if((e1.getX()-e2.getX())>FLIP_DISTANCE) {
            viewFlipper.setInAnimation(this, R.anim.slide_in_right);
            viewFlipper.setOutAnimation(this, R.anim.slide_out_left);
            viewFlipper.showPrevious();
            return true;
        }
        else if((e2.getX()-e1.getX())>FLIP_DISTANCE) {
            viewFlipper.setInAnimation(this,R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this, R.anim.slide_out_right);
            viewFlipper.showNext();
            return true;
        }
        else
            return false;

    }

    /**
     * 所有手势的监听处理设置，交给gestureEvent的onTouchEvent（）方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    //订单列表的相关设置
    /**
     * 初始化订单列表
     * @param number 订单数量
     * @return 放回订单列表
     */
    public List<Order> ordersInit(int number){
        List<Order> orders = new ArrayList<>();
        for(int i=0;i<number;i++){
            Order order = new Order();
            ArrayList<Food> foods = (ArrayList<Food>) listFoodInit(5);
            order.setIsHistory(Math.random()>0.5);
            order.setIsHander(Math.random()>0.5);
            order.setFood(foods);
            order.setDate(new Date());
            order.setNumber(String.valueOf((long)(Math.random()*100000000000L)));
            orders.add(order);
        }
        return orders;
    }

    /**
     * 初始化食物列表
     * @param number 食物个数
     * @return 返回食物列表
     */
    public List<Food> listFoodInit(int number){
        List<Food> foodList = new ArrayList<>();
        for(int i =0;i<number;i++){
            Food food = new Food("菜品"+(i+1),"菜品"+(i+1)+"的简介",(int)(Math.random()*100),1);
            foodList.add(food);
        }
        return foodList;
    }

    /**
     * 将orders划分成正在交易和历史订单
     * @param orders
     */
    public void historyOrder(List<Order> orders){
        for(Order item:orders){
            if(item.isHistory()){
                historyOrders.add(item);
            }else{
                tradingOrders.add(item);
            }
        }
    }

    /**
     * 按键的监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.bt_trading):
                trading_order_list.setVisibility(View.VISIBLE);
                history_order_list.setVisibility(View.GONE);
                break;
            case(R.id.bt_history):
                trading_order_list.setVisibility(View.GONE);
                history_order_list.setVisibility(View.VISIBLE);
                break;
            case (R.id.my_image):
            case (R.id.login_tv_myinfo):
                startActivityForResult(new Intent(MainActivity.this,Login.class),10);
            default:
                trading_order_list.setVisibility(View.GONE);
                history_order_list.setVisibility(View.GONE);
                break;
        }
    }
}
