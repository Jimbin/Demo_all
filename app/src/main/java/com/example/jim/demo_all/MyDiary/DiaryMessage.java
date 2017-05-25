package com.example.jim.demo_all.MyDiary;

/**
 * Created by ZHENGYU on 2016/12/19.
 */

public class DiaryMessage {
    String title;
    String time;
    String message;
    public DiaryMessage(String title,String time,String message){
        this.time = time;
        this.title = title;
        this.message= message;
    }

    public String getTitle(){
        return  title;
    }

    public String getTime(){
        return time;
    }
    public String getMessage(){
        return message;
    }
}
