package com.tabjin.administrator.dinningonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by Administrator on 2016/1/24.
 */
public class HomePageActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private ViewFlipper viewFlipper;
    private GestureDetector gestureDetector;
    final int FLIP_DISTANCE = 100;

    /**
     * activity 启动时的操作
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        viewFlipper = (ViewFlipper) findViewById(R.id.vf_homepages);
        gestureDetector = new GestureDetector(this,this);
        auto(viewFlipper);
    }

    public void allfood(View view){
        Intent intent = new Intent();
        intent.setClass(HomePageActivity.this, AllfoodActivity.class);
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


}
