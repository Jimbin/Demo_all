package com.example.jim.demo_all;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.jim.demo_all.Animator.AnimatorActivity;
import com.example.jim.demo_all.Bmob.BmobTestActivity;
import com.example.jim.demo_all.CustomView.CustomActivity;
import com.example.jim.demo_all.MyDiary.MyDiaryActivity;
import com.example.jim.demo_all.MyPhone.ContactInfoActivity;
import com.example.jim.demo_all.fragment.Activity3;
import com.example.jim.demo_all.present.ActivityCollector;
import com.example.jim.demo_all.present.SensorManagerHelper;
import com.example.jim.demo_all.present.volley_test;
import com.example.jim.demo_all.weight_calcuate.Weight_Cal_Activity;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.listener.ValueEventListener;

import static android.widget.Toast.makeText;

public class MainActivity extends BaseActivity {
    private String data=null;
    private int count=0;
    private Button btn_send;
    private EditText editText;
    private ListView listview_sth;
    private RequestQueue mQueue;
    private ArrayList<String> namelist=new ArrayList<String>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "80c583efe08701c08a2c323302339220");
        final BmobRealTimeData bdata=new BmobRealTimeData();
        bdata.start(new ValueEventListener() {
            @Override
            public void onConnectCompleted(Exception e) {
                Log.d("bmob", "连接成功:"+bdata.isConnected());
                if (bdata.isConnected()){
                    bdata.subRowUpdate("Running_Count","98ac203ffb");
                }
            }

            @Override
            public void onDataChange(JSONObject jsonObject) {
                if (BmobRealTimeData.ACTION_UPDATEROW.equals(jsonObject.optString("updateRow"))){
                makeText(MainActivity.this,"this,gengxinle",Toast.LENGTH_LONG).show();
                }
            }
        });

        mQueue = Volley.newRequestQueue(MainActivity.this);

        initview();
        initListviewData();
        initListview();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=editText.getText().toString();
                Activity2.actionStart(MainActivity.this,data);
                save(data);
            }
        });

        SensorManagerHelper sensorHelper = new SensorManagerHelper(this);
        sensorHelper.setOnShakeListener(new SensorManagerHelper.OnShakeListener() {

            @Override
            public void onShake() {
                makeText(MainActivity.this, "你在摇哦", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListview() {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,namelist);
        listview_sth.setAdapter(adapter);
        listview_sth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (namelist.get(position).equals("Add web")){
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.baidu.com"));
                    startActivity(intent);
                    makeText(MainActivity.this,"Click the add item", Toast.LENGTH_SHORT).show();
                }if (namelist.get(position).equals("Fragment change")){
                    Intent intent3 =new Intent(MainActivity.this,Activity3.class);
                    startActivity(intent3);
                }if (namelist.get(position).equals("Weight calcuate")){
                    Intent intent4 =new Intent(MainActivity.this,Weight_Cal_Activity.class);
                    startActivity(intent4);
                }if (namelist.get(position).equals("Myphone")){
                    Intent intent5 =new Intent(MainActivity.this,ContactInfoActivity.class);
                    startActivity(intent5);
                }if (namelist.get(position).equals("MyDiary")){
                    Intent intent5 =new Intent(MainActivity.this,MyDiaryActivity.class);
                    startActivity(intent5);
                }if (namelist.get(position).equals("Bmob")){
                    Intent intent6 =new Intent(MainActivity.this,BmobTestActivity.class);
                    startActivity(intent6);
                }if (namelist.get(position).equals("CustomView")){
                    Intent intent6 =new Intent(MainActivity.this,CustomActivity.class);
                    startActivity(intent6);
                }if (namelist.get(position).equals("Animator")){
                    Intent intent6 =new Intent(MainActivity.this,AnimatorActivity.class);
                    startActivity(intent6);
                }if (namelist.get(position).equals("volley")){
                    volley_test volley_test1=new volley_test();
                    volley_test1.Get();
                    mQueue.add(volley_test1.stringRequest);
                }if (namelist.get(position).equals("RecycleView")){
                    Intent intent=new Intent(MainActivity.this,RecycleActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initview() {
        btn_send= (Button) findViewById(R.id.btn_sendmain);
        editText= (EditText) findViewById(R.id.edittextmain);
        listview_sth= (ListView) findViewById(R.id.lisview_sth);
    }

    private void initListviewData() {
        namelist.add("Add web");
        namelist.add("Fragment change");
        namelist.add("Weight calcuate");
        namelist.add("Myphone");
        namelist.add("MyDiary");
        namelist.add("Bmob");
        namelist.add("CustomView");
        namelist.add("Animator");
        namelist.add("volley");
        namelist.add("RecycleView");
    }

    //读message.txt文件里面的东西
    public String read() {
        try {
            FileInputStream inStream = this.openFileInput("message.txt");
            byte[] buffer = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((hasRead = inStream.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, hasRead));
            }
            inStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //写文件
    public void save(String inputtext) {
        try {
            // 步骤2:创建一个FileOutputStream对象,MODE_APPEND追加模式
            FileOutputStream fos = openFileOutput("message.txt",
                    MODE_APPEND);
            // 步骤3：将获取过来的值放入文件
            fos.write(inputtext.getBytes());
            // 步骤4：关闭数据流
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast toast;
        switch (item.getItemId()){
            case R.id.addweb_id:
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                toast=Toast.makeText(this,"Click the add item", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER|Gravity.RIGHT,0,0);
                toast.show();
                break;
            case R.id.dialog_id:
                //Toast.makeText(this,"Click the dialog item",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("this is a dialog");
                dialog.setMessage("something");//内容
                dialog.setCancelable(false);//可否取消
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.setNegativeButton("ProgressDialog", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                        progressDialog.setTitle("this is a progressdialog");
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(true);
                        progressDialog.show();
                    }
                });
                dialog.show();
                break;
            case R.id.exit_id:
                ActivityCollector.finishAll();
//                makeText(this,"Click the exit item",Toast.LENGTH_SHORT).show();
//                LayoutInflater inflater = getLayoutInflater();
////# 解析自定义布局，父布局传入肯
//                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
////# 填充信息，比如设置文字，图片等
//                TextView text = (TextView) layout.findViewById(R.id.text);
//                text.setText("This is a custom Toast.");
////# 显示 Toast
//                toast = new Toast(getApplicationContext());
//                toast.setDuration(Toast.LENGTH_LONG);
//                toast.setView(layout);
//                toast.show();
                break;
            case R.id.listview_id:
                Intent intent2 =new Intent(MainActivity.this,Activity2.class);
                startActivity(intent2);break;
            case R.id.fragment_id:
                Intent intent3 =new Intent(MainActivity.this,Activity3.class);
                startActivity(intent3);break;
            case R.id.handler_id:
                Intent intent4 =new Intent(MainActivity.this,Activity2  .class);
                startActivity(intent4);break;
            case R.id.spinner_id:
                Intent intent5 =new Intent(MainActivity.this,Spinner_Activity.class);
                startActivity(intent5);break;
                default:
                    break;
        }
        return true;
    }
}


