package com.example.jim.demo_all.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jim.demo_all.R;

public class Activity3 extends AppCompatActivity {

    private int sign=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Button button= (Button) findViewById(R.id.change_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sign==0){
                    sign=1;
                UpFragment2 upFragment2=new UpFragment2();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.up_layout,upFragment2);
                transaction.commit();}
                else {
                    sign=0;
                    UpFragment upFragment=new UpFragment();
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.up_layout,upFragment);
                    transaction.commit();}
            }
        });
    }
}
