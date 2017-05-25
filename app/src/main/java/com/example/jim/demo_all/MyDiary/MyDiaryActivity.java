package com.example.jim.demo_all.MyDiary;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.jim.demo_all.R;

import java.util.ArrayList;
import java.util.List;

public class MyDiaryActivity extends AppCompatActivity {
    private List<DiaryMessage> DiaryList = new ArrayList<DiaryMessage>();
    private ListView listview;
    private DiaryAdapter adapter;
    private Context context;
    private DiaryMessage diary;
    private DiaryMessageHelper dbHelper;
    SQLiteDatabase db;

    private String title;
    private String time;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);
        dbHelper = new DiaryMessageHelper(this,"diary.db",null,1);
        dbHelper.getWritableDatabase();
        db = dbHelper.getWritableDatabase();
        set();
        get();

        listview = (ListView)findViewById(R.id.listview_mydiary);
        adapter = new DiaryAdapter(this,R.id.listview_mydiary,DiaryList);
        listview.setAdapter(adapter);
    }

    public void set(){
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        time = intent.getStringExtra("time");
        message = intent.getStringExtra("message");
        if(title!=null&&time!=null&&message!=null){
            ContentValues values = new ContentValues();
            values.put("title", title);
            values.put("time", time);
            values.put("message", message);
            db.insert("diary", null, values);
        }
    }

    public void get(){
        SQLiteDatabase dataBase=db;
        String table="diary";
        String[] columns={"title","time","message"};//要的数据

        Cursor cursor=dataBase.query(table, columns, null, null, null, null, null);
        while(cursor.moveToNext())
        {
            int nameColumnIndex = cursor.getColumnIndex("title");
            title=cursor.getString(nameColumnIndex);
            nameColumnIndex = cursor.getColumnIndex("time");
            time=cursor.getString(nameColumnIndex);
            nameColumnIndex = cursor.getColumnIndex("message");
            message = cursor.getString(nameColumnIndex);
            diary = new DiaryMessage(title,time,message);
            if(!DiaryList.equals(diary))
                DiaryList.add(diary);
        }
        cursor.close();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu); 第一种使用方式
        // 设置菜单选项一般最好设置低于 6个选项
        MenuItem menuItem = menu.add(1001, 100, 1, "add");
        // menuItem.setIcon(R.drawable.ic_launcher); //高版本中不建议添加图标。添加了图标也不会显示的。

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 100:
                Intent intent = new Intent(MyDiaryActivity.this,AddDiary.class);
                startActivity(intent);
                finish();
                //Toast.makeText(MainActivity.this, "点击菜单一选项", 3).show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
