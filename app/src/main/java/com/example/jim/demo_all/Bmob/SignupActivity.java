package com.example.jim.demo_all.Bmob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jim.demo_all.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SignupActivity extends AppCompatActivity {
private EditText bmobaccount,bmobpassword,bmobpassword2,bmobemail;
    private Button btn_signup;
    private String account,password,password2,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        bmobaccount= (EditText) findViewById(R.id.et_account);
        bmobpassword= (EditText) findViewById(R.id.et_password);
        bmobpassword2= (EditText) findViewById(R.id.et_password2);
        bmobemail= (EditText) findViewById(R.id.et_email);

        account=bmobaccount.getText().toString();
        password=bmobpassword.getText().toString();
        password2=bmobpassword2.getText().toString();
        email=bmobemail.getText().toString();

        btn_signup= (Button) findViewById(R.id.signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser user = new BmobUser();
                user.setUsername(account);
                user.setPassword(password);
                user.setEmail(email);
                user.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser o, BmobException e) {
                        if (e==null){Toast.makeText(SignupActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    }else {
                            Log.e("something", "done: "+e);
                        }
                    }
                });
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
