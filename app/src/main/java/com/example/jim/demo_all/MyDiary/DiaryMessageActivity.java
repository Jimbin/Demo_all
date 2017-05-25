package com.example.jim.demo_all.MyDiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jim.demo_all.R;

/**
 * Created by ZHENGYU on 2016/12/19.
 */

public class DiaryMessageActivity extends Activity{
    private TextView tiele;
    private TextView time;
    private TextView message;
    private String titleText;
    private String timeText;
    private String messageText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_message);
        tiele = (TextView)findViewById(R.id.title_diary);
        time = (TextView)findViewById(R.id.tv_timediary);
        message = (TextView)findViewById(R.id.message_diary);
        Intent intent = getIntent();
        titleText = intent.getStringExtra("title");
        timeText = intent.getStringExtra("time");
        messageText = intent.getStringExtra("message");
        tiele.setText(titleText);
        time.setText(timeText);
        message.setText(messageText);
    }

}
