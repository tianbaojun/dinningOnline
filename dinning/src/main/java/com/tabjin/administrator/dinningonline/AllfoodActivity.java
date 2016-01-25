package com.tabjin.administrator.dinningonline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/24.
 */
public class AllfoodActivity extends AppCompatActivity {

    public ListView allfoodList;
    SimpleAdapter allfoodListAdapter;
    private List<Map<String,Object>> list_items;
    private Map<String,Object> map_item;
    private int[] images = null;
    private ArrayList<String> name = null;
    public SharedPreferences itemType;
    public SharedPreferences.Editor itemTypeEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allfood);
        name = new ArrayList<>();
        list_items = new ArrayList<>();
        itemType = getSharedPreferences("dinning",MODE_PRIVATE);
        itemTypeEditor = itemType.edit();
        for(int i=0;i<40;i++){
            name.add("品种"+(i+1));
            map_item = new HashMap<>();
            map_item.put("header",R.drawable.forpeople);
            map_item.put("name",name.get(i));
            list_items.add(map_item);
        }
        allfoodList = (ListView) findViewById(R.id.list_allfood);
        allfoodListAdapter = new SimpleAdapter(this,list_items,
                R.layout.allfood_type,new String[]{"header","name"},
                new int[]{R.id.image_allfood_type,R.id.name_allfood_type});
        allfoodList.setAdapter(allfoodListAdapter);
        allfoodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(AllfoodActivity.this,AllfoodItemsActivity.class));
                itemTypeEditor.putString("itemTypeName", name.get(position));
                itemTypeEditor.commit();
            }
        });
    }

}
