package com.example.jim.demo_all.MyPhone;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jim.demo_all.R;

public class ContactInfoActivity extends Activity {
    private Button btn_take;
    private Button btn_call;
    private Button btn_send;
    public TextView tv_show;
    public EditText editText;
public static  String name;
public static  String number;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        //找到相应的控件
        btn_take = (Button) findViewById(R.id.btn_take);
        btn_call = (Button) findViewById(R.id.btn_call);
        btn_send = (Button) findViewById(R.id.btn_sendMessage);
        tv_show=(TextView) findViewById(R.id.tv_show);
        editText= (EditText) findViewById(R.id.edittext_phone);
        //为button按钮设置单击事件
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.toString()!=null){
                    if (ActivityCompat.checkSelfPermission(ContactInfoActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 111);
                        }
                        return;}
                    String phoneNumber = editText.getText().toString();
                    //意图：想干什么事
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    //url:统一资源定位符
                    //uri:统一资源标示符（更广）
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    //开启系统拨号器
                    startActivity(intent);
                }
                else Toast.makeText(ContactInfoActivity.this,"请输入号码或选择联系人",Toast.LENGTH_SHORT).show();
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.toString()!=null){
                    String content =tv_show.getText().toString();
                    String content2 =editText.getText().toString();
                    //创建Intent对象，来传递数据
                    Intent intent1=new Intent(ContactInfoActivity.this,SendMessage.class);
                    intent1.putExtra("content",content);
                    intent1.putExtra("content2",content2);

                    startActivity(intent1);
                }
                else Toast.makeText(ContactInfoActivity.this,"请输入号码或选择联系人",Toast.LENGTH_SHORT).show();
            }
        });
        btn_take.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (ActivityCompat.checkSelfPermission(ContactInfoActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{"android.permission.READ_CONTACTS"}, 111);
                    }
                    return;}

                //实现activity的跳转，并返回结果
                Intent intent = new Intent(ContactInfoActivity.this,SecondActivity.class);
                //设置结果码为0
                startActivityForResult(intent, 0);

            }
        });
    }
    //返回结果必须要重写onActivityResult()方法
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //判断data是否为空
        if(data!=null)
        {
            //判断结果码是否为0
            if (resultCode == 0)
            {
                //为TextView设置文字
                tv_show.setText(data.getStringExtra("content"));
                editText.setText(data.getStringExtra("content2"));

            }
        }
    }
}
