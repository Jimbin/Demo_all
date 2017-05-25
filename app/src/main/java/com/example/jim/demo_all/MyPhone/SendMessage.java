package com.example.jim.demo_all.MyPhone;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jim.demo_all.R;

import java.util.List;

import static android.os.Build.VERSION_CODES.M;

public class SendMessage extends Activity {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private EditText editText;
    private Button button;
    private String phone;
    private String context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        textView1= (TextView) findViewById(R.id.name_text);
        textView2= (TextView) findViewById(R.id.number_test);
        editText= (EditText) findViewById(R.id.editText22);
        context = editText.getText().toString();
        button= (Button) findViewById(R.id.button);
        Intent data=getIntent();
        phone=data.getStringExtra("content2");
        textView1.setText(data.getStringExtra("content"));
        textView2.setText("号码："+phone);
        //context = editText.toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (ActivityCompat.checkSelfPermission(SendMessage.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                        if (Build.VERSION.SDK_INT >= M) {
                            requestPermissions(new String[]{"android.permission.SEND_SMS"}, 111);
                        }
                        return;}
                sendSMS(phone,context);
                /*Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phone));
                intent.putExtra("sms_body", context);
                startActivity(intent);*///调用系统的短信系统
                    /*SmsManager manager = SmsManager.getDefault();
                    ArrayList<String> list = manager.divideMessage(context);  //因为一条短信有字数限制，因此要将长短信拆分
                    for(String text:list){
                        manager.sendTextMessage(phone, null, text, null, null);
                    }*/
                    Toast.makeText(getApplicationContext(), "发送完毕", Toast.LENGTH_SHORT).show();
                    //String smsContent = editText.getText().toString();
                    //得到默认的短信管理器
                    /*SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(phone, null, context, null, null);
                    //提示用户信息,临时性通知
                    Toast toast = Toast.makeText(SendMessage.this, "发送完成", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.RIGHT, 50, 100);
                    toast.show();*/

                //else Toast.makeText(SendMessage.this,"请输入短信内容",Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void sendSMS(String phoneNumber,String message){
        //获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
    }

}
