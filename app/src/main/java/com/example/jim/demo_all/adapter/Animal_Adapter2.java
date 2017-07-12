package com.example.jim.demo_all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jim.demo_all.Bean.Animals;
import com.example.jim.demo_all.R;

import java.util.List;

/**
 * Created by Jim斌 on 2017/7/12.
 */

public class Animal_Adapter2 extends BaseAdapter {
    private List<Animals>list;
    private LayoutInflater layoutInflater;

    public Animal_Adapter2(Context context, List<Animals> animallist){
        list=animallist;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= layoutInflater.inflate(R.layout.animal_item,null);
            viewHolder.title= (TextView) convertView.findViewById(R.id.animal_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(list.get(position).getName());
        return convertView;
    }

    class ViewHolder{
        TextView title;
    }

}