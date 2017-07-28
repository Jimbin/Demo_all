package com.example.jim.blockcanary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BlockCanaryActivity extends AppCompatActivity  {
    private Button btn_ANR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_canary);

        btn_ANR= (Button) findViewById(R.id.btn_ANR);
        btn_ANR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(11*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"点击成功",Toast.LENGTH_LONG).show();
            }
        });
    }


}
