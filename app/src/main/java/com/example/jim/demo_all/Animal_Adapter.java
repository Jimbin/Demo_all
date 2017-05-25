package com.example.jim.demo_all;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jim斌 on 2017/2/11.
 */

public class Animal_Adapter extends ArrayAdapter <Animals>{
    private int resourceId;

    public Animal_Adapter(Context context, int resource, List<Animals> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Animals animals= (Animals) getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.animalImage1= (ImageView) view.findViewById(R.id.animal_image);
            viewHolder.animalname1= (TextView) view.findViewById(R.id.animal_name);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.animalImage1.setImageResource(animals.getImageId());
        viewHolder.animalname1.setText(animals.getName());
        return view;
    }
    class ViewHolder{
        ImageView animalImage1;
        TextView animalname1;
    }
}
