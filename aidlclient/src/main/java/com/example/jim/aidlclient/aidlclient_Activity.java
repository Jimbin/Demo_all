package com.example.jim.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jim.demo_all.aidl.My_AidlInterface;

public class aidlclient_Activity extends AppCompatActivity  {

    private EditText n1,n2;
    private TextView num;
    private Button add;
    My_AidlInterface my_aidlinterface;
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            my_aidlinterface=My_AidlInterface.Stub.asInterface(service);//拿到远程服务返回的ibinder对象service
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            my_aidlinterface=null;//回收资源
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlclient_);

        initview();
        bindTheService();
    }


    private void initview() {
        n1= (EditText) findViewById(R.id.n1);
        n2= (EditText) findViewById(R.id.n2);
        num= (TextView) findViewById(R.id.num);
        add= (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1= Integer.parseInt(n1.getText().toString());
                int num2= Integer.parseInt(n2.getText().toString());
                try {
                    int sum=my_aidlinterface.add(num1,num2);
                    Log.d("等于", "onClick: "+sum);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    private void bindTheService() {

        Log.d("lalala", "bindtherservice(): ");
        Intent intent=new Intent();
        intent.setComponent(new ComponentName
                ("com.example.jim.demo_all", "com.example.jim.demo_all.aidl_Service"));
        //想要绑定服务的包名跟类名(类名要具体)
        bindService(intent,conn, Context.BIND_AUTO_CREATE);//第二个参数是绑定服务时的回调，第三个参数是一个标志
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
