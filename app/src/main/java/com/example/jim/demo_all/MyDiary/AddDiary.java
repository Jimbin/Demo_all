package com.example.jim.demo_all.MyDiary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jim.demo_all.R;

/**
 * Created by ZHENGYU on 2016/12/19.
 */

public class AddDiary extends Activity{
    private Context context;
    private EditText editText;
    private EditText message;
    private Button send;
    private Button search;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_diary);
        context = this;
        init();
    }
    public void init(){
        editText = (EditText)findViewById(R.id.editTitle_diary);
        message = (EditText)findViewById(R.id.editMessage_diary);
        send = (Button)findViewById(R.id.btn_commitDiary);
        search = (Button)findViewById(R.id.btn_searchDiary);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().equals("")&&!message.getText().toString().equals("")){
                    Intent intent = new Intent(AddDiary.this,MyDiaryActivity.class);
                    intent.putExtra("title",editText.getText().toString());
                    intent.putExtra("message",message.getText().toString());
                    intent.putExtra("time", DateFormat.format("MM/dd/yy hh:mm:ss", System.currentTimeMillis()));
                    startActivity(intent);
                }
                else {
                    Toast.makeText(context,"请填写完整信息！！！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDiary.this,MyDiaryActivity.class);
                startActivity(intent);
            }
        });

    }

}
