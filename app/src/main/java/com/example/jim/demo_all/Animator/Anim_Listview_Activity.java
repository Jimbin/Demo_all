package com.example.jim.demo_all.Animator;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.jim.demo_all.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.jim.demo_all.R.id.anim_listView;

public class Anim_Listview_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim__listview_);

        List<Drawable> drawables=new ArrayList<>();
        drawables.add(getResources().getDrawable(R.drawable.one));
        drawables.add(getResources().getDrawable(R.drawable.two));
        drawables.add(getResources().getDrawable(R.drawable.three));
        drawables.add(getResources().getDrawable(R.drawable.four));
        drawables.add(getResources().getDrawable(R.drawable.five));
        drawables.add(getResources().getDrawable(R.drawable.six));
        drawables.add(getResources().getDrawable(R.drawable.seven));
        drawables.add(getResources().getDrawable(R.drawable.eight));
        drawables.add(getResources().getDrawable(R.drawable.night));
        drawables.add(getResources().getDrawable(R.drawable.ten));

        ListView animlistview= (ListView) findViewById(anim_listView);
        Anim_List_Adapter anim_list_adapter=new Anim_List_Adapter(this,animlistview,drawables,300);
        animlistview.setAdapter(anim_list_adapter);
    }

}
