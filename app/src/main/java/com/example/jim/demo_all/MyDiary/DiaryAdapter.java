package com.example.jim.demo_all.MyDiary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jim.demo_all.R;

import java.util.List;

/**
 * Created by ZHENGYU on 2016/12/19.
 */

public class DiaryAdapter  extends BaseAdapter {
    private int resoutceId;
    private List<DiaryMessage>list;
    private Context parentContext;

    public DiaryAdapter(Context context, int resource,List<DiaryMessage>list) {
        this.resoutceId = resource;
        this.list=list;
        this.parentContext = context;
    }
    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View converView, ViewGroup parent){
        TextView text;
        String diary = list.get(position).getTitle();
        if(converView==null) {
            LayoutInflater inflater = LayoutInflater.from(parentContext);
            converView = inflater.inflate(R.layout.diary_listview_item, null);
            text = (TextView) converView.findViewById(R.id.textView);
            converView.setTag(text);
        }
        else{
           text = (TextView) converView.getTag();
        }
        text.setText(diary);
        final int i = position;
        converView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentContext,DiaryMessageActivity.class);
                intent.putExtra("title",list.get(i).getTitle());
                intent.putExtra("time",list.get(i).getTime());
                intent.putExtra("message",list.get(i).getMessage());
                parentContext.startActivity(intent);
            }
        });
        return converView;
    }

}
