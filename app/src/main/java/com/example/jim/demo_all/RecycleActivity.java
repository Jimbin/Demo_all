package com.example.jim.demo_all;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jim.demo_all.adapter.Recycle_Adapter;

import java.util.ArrayList;
import java.util.List;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class RecycleActivity extends AppCompatActivity {
//    RecyclerView mRecyclerView;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recylerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        //实例化并传输数据给adapter
        Recycle_Adapter adapter = new Recycle_Adapter(getApplicationContext(), list);
        mRecyclerView.setAdapter(adapter);

    }
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("item" + i);
        }
    }

}
