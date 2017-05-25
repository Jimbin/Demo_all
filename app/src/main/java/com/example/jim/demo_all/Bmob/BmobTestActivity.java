package com.example.jim.demo_all.Bmob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jim.demo_all.R;

import java.util.ArrayList;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class BmobTestActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBmob_add,btnBmob_del,btnBmob_change,btnBmob_search;
    private ListView Bmoblistview;
    private long clickTime=0;

    private ArrayList<String> bmoblist=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmob_test);
        btnBmob_add= (Button) findViewById(R.id.btn_Bmob_add);
        btnBmob_del= (Button) findViewById(R.id.btn_Bmob_del);
        btnBmob_change= (Button) findViewById(R.id.btn_Bmob_change);
        btnBmob_search= (Button) findViewById(R.id.btn_Bmob_search);

        Bmoblistview= (ListView) findViewById(R.id.Bmob_listView);
        bmoblist.add("登录注册功能测试");
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(BmobTestActivity.this,
                android.R.layout.simple_list_item_1,bmoblist);
        Bmoblistview.setAdapter(adapter);
        Bmoblistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (bmoblist.get(position).equals("登录注册功能测试")){
                    Intent intent=new Intent(BmobTestActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnBmob_add.setOnClickListener(BmobTestActivity.this);
        btnBmob_del.setOnClickListener(BmobTestActivity.this);
        btnBmob_change.setOnClickListener(BmobTestActivity.this);
        btnBmob_search.setOnClickListener(BmobTestActivity.this);
       // String icon= Environment.

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再次点击退出",  Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            //Log.e(TAG, "exit application");
            this.finish();
            System.exit(0);
        }
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_Bmob_add:
                Person p=new Person();
                p.setName("Jim");
                p.setAddress("华南师范大学西三410");
                p.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(BmobTestActivity.this,"添加数据成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(BmobTestActivity.this,"添加数据失败",Toast.LENGTH_SHORT).show();

                        }
                    }
                });break;
            case R.id.btn_Bmob_del:
                Person p1=new Person();
                p1.setObjectId("99d87bf648");
                p1.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null){
                            Toast.makeText(BmobTestActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(BmobTestActivity.this,"删除失败",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                break;
            case R.id.btn_Bmob_change:
                Person p2 = new Person();
                p2.setAddress("西三");
                p2.update("99d87bf648", new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(BmobTestActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(BmobTestActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                break;
            case R.id.btn_Bmob_search:
                BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
                bmobQuery.getObject("99d87bf648", new QueryListener<Person>() {
                    @Override
                    public void done(Person person, BmobException e) {
                        if (e==null){
                            Toast.makeText(BmobTestActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(BmobTestActivity.this,"查询失败",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                break;
            default:break;
        }
    }

}
