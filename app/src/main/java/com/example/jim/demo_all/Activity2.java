package com.example.jim.demo_all;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Activity2 extends BaseActivity implements View.OnClickListener {
    public static final int UPDATE = 1;
    private String data1="abc";
    private static EditText editText;
    private Button button_handler;
    private List<Animals> animalsList=new ArrayList<Animals>();
    private final MyHandler mhandler=new MyHandler(this);
//    private static Handler handler=new Handler() {
//        public void handleMessage(Message msg){
//                switch (msg.what){
//                    case 100:
//                        editText.setText("handler");break;
//                    default:break;
//                }
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        initAnimals();
        Animal_Adapter adapter_a=new Animal_Adapter(Activity2.this,R.layout.animal_item,animalsList);
        ListView listview= (ListView) findViewById(R.id.listview2_id);
        listview.setAdapter(adapter_a);

        editText= (EditText) findViewById(R.id.account1_id);
        Intent intent3=getIntent();
         data1=intent3.getStringExtra("part1");
        editText.setText(data1);

        button_handler= (Button) findViewById(R.id.btn_handler);
        button_handler.setOnClickListener(Activity2.this);

        Toast.makeText(this,data1,Toast.LENGTH_SHORT).show();


    }
    private void initAnimals() {
        Animals one=new Animals("one",R.drawable.one);
        animalsList.add(one);
        Animals two=new Animals("two",R.drawable.two);
        animalsList.add(two);
        Animals three=new Animals("three",R.drawable.three);
        animalsList.add(three);
        Animals four=new Animals("four",R.drawable.four);
        animalsList.add(four);
        Animals five=new Animals("five",R.drawable.five);
        animalsList.add(five);
        Animals six=new Animals("six",R.drawable.six);
        animalsList.add(six);
        Animals seven=new Animals("seven",R.drawable.seven);
        animalsList.add(seven);
        Animals eight=new Animals("eight",R.drawable.eight);
        animalsList.add(eight);
        Animals night=new Animals("night",R.drawable.night);
        animalsList.add(three);
        Animals ten=new Animals("three",R.drawable.ten);
        animalsList.add(ten);
    }
    public static void actionStart(Context context, String data1){
        Intent intent=new Intent(context,Activity2.class);
        intent.putExtra("part1",data1);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_handler:
                Toast.makeText(Activity2.this,"handler 测试开始",Toast.LENGTH_SHORT).show();
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        Message msg=new Message();
                        msg.what=100;
                        mhandler.sendMessageAtTime(msg,0);
                    }
                };
                        break;

            default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    private static class MyHandler extends Handler {
        private final WeakReference<Activity2> mActivity;

        public MyHandler(Activity2 activity) {
            mActivity = new WeakReference<Activity2>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            System.out.println(msg);
            if (mActivity.get() == null) {
                switch (msg.what){
                    case 100:
                       editText.setText("handler");break;
                    default:break;
                }
                return;
            }
        }
    }
}
