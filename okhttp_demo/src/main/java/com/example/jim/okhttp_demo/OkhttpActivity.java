package com.example.jim.okhttp_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {
//    private Button getBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
//        getBtn= (Button) findViewById(R.id.getbtn);
    }
    public void doGet(View view){
        //1拿到OKHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //2.构造Request
        Request.Builder builder=new Request.Builder();
        Request request=builder.get().url("http://dict.youdao.com/w/response/#keyfrom=dict2.top").build();
        //3.将request对象封装为call
        okhttp3.Call call=okHttpClient.newCall(request);
        //4.执行call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Okhttp:", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("Okhttp:", "success: ");
//                Log.d("Okhttp:", "success: "+response.toString());
            }
        });
    }
}
