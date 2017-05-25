package com.example.jim.demo_all.Bmob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jim.demo_all.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
private Button btn_login,btn_signin;
    private EditText et_account,et_password;
    private String loginaccount,loginpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_signin= (Button) findViewById(R.id.btn_signup);
        btn_login.setOnClickListener(LoginActivity.this);
        btn_signin.setOnClickListener(LoginActivity.this);
        et_account= (EditText) findViewById(R.id.login_account);
        et_password= (EditText) findViewById(R.id.login_password);

        loginaccount=et_account.getText().toString();
        loginpassword=et_password.getText().toString();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                BmobUser user=new BmobUser();
                user.setUsername("loginaccount");
                user.setPassword("loginpassword");
                user.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser o, BmobException e) {
                        if (e==null){
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                        }else {

                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.btn_signup:
                Toast.makeText(LoginActivity.this,"去注册",Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent2);
                break;
            default:break;
        }
    }
}
