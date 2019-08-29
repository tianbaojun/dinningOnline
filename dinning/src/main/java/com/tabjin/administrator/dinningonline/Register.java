package com.tabjin.administrator.dinningonline;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tabjin.administrator.dinningonline.bean.UserEntry;
import com.tabjin.administrator.dinningonline.util.DatabaseHelper;


/**
 * Created by Administrator on 2016/1/21.
 */
public class Register extends AppCompatActivity {

    private static EditText user;
    private static EditText password;
    private static RadioGroup gender;
    private static RadioButton male;
    private static RadioButton female;
    private static CheckBox ch_read;
    private static CheckBox ch_sport;
    private static CheckBox ch_speak;
    private static CheckBox ch_zhai;
    private static CheckBox ch_art;
    private static EditText description;
    private static Button re_submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        user = (EditText) findViewById(R.id.register_username);
        password = (EditText) findViewById(R.id.register_password);
        gender = (RadioGroup) findViewById(R.id.gender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        ch_read = (CheckBox) findViewById(R.id.ch_reader);
        ch_sport = (CheckBox) findViewById(R.id.ch_sport);
        ch_speak = (CheckBox) findViewById(R.id.ch_speak);
        ch_zhai = (CheckBox) findViewById(R.id.ch_zhai);
        ch_art = (CheckBox) findViewById(R.id.ch_art);
        description = (EditText) findViewById(R.id.description);
        re_submit = (Button) findViewById(R.id.re_submit);

    }


    public void submit(View view){

        //创建数据库
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(Register.this,"userInfo_db");
        SQLiteDatabase userInfo_db = dbHelper.getWritableDatabase();

        //创建contentValues
        ContentValues userValues = new ContentValues();

        //创建用户类
        UserEntry userEntry = new UserEntry();

        //将数据加入用户类和userValues
        userEntry.setName(user.getText().toString());
        userValues.put("name", userEntry.getName());
        userEntry.setPassword(password.getText().toString());
        userValues.put("password", userEntry.getPassword());
        userEntry.setGender(gender.getCheckedRadioButtonId() == R.id.male ? '男' : '女');
        userValues.put("gender", String.valueOf(userEntry.getGender()));
        userEntry.setInterest(ch_read.getText().toString() + "," + ch_sport.toString() + "," +
                ch_speak.getText().toString() + "," + ch_zhai.getText().toString() + "," +
                ch_art.getText().toString());
        userValues.put("interest", userEntry.getInterest());
        userEntry.setDescription(description.getText().toString());

        //将文件插入数据库
        userValues.put("description", userEntry.getDescription());

        //判断是否注册成功并实现跳转到登录界面
        if(userInfo_db.insert("userInfo", null, userValues)!=-1) {
            Toast.makeText(getApplicationContext(), "注册成功！",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(Register.this,Login.class);
            startActivity(intent);
        }

    }
}
