package com.example.jim.demo_all.MyDiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ZHENGYU on 2016/12/19.
 */

public class DiaryMessageHelper extends SQLiteOpenHelper{

    private Context context;

    public static final String MyDiary = "create table diary ("

            +"id integer primary key autoincrement, "
            +"title text, "+"time text, "+"message text)";


    public DiaryMessageHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyDiary);
        Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
