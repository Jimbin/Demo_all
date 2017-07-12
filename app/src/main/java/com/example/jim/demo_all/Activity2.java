package com.example.jim.demo_all;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jim.demo_all.Bean.Animals;
import com.example.jim.demo_all.adapter.Animal_Adapter2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity2 extends BaseActivity implements View.OnClickListener {
    public static final int UPDATE = 1;
    public static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    private String data1="abc";
    private static EditText editText;
    private Button button_handler;
    private List<Animals> animalsList=new ArrayList<Animals>();
    private final MyHandler mhandler=new MyHandler(this);
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
//        initAnimals();
//        Animal_Adapter adapter_a=new Animal_Adapter(Activity2.this,R.layout.animal_item,animalsList);
        listview= (ListView) findViewById(R.id.listview2_id);
//        listview.setAdapter(adapter_a);
        editText= (EditText) findViewById(R.id.account1_id);
        Intent intent3=getIntent();
         data1=intent3.getStringExtra("part1");
        editText.setText(data1);

        button_handler= (Button) findViewById(R.id.btn_handler);
        button_handler.setOnClickListener(Activity2.this);

        Toast.makeText(this,data1,Toast.LENGTH_SHORT).show();

        new My_Asyntask().execute(URL);
    }
//    private void initAnimals() {
//        Animals one=new Animals("one",R.drawable.one);
//        animalsList.add(one);
//        Animals two=new Animals("two",R.drawable.two);
//        animalsList.add(two);
//        Animals three=new Animals("three",R.drawable.three);
//        animalsList.add(three);
//        Animals four=new Animals("four",R.drawable.four);
//        animalsList.add(four);
//        Animals five=new Animals("five",R.drawable.five);
//        animalsList.add(five);
//        Animals six=new Animals("six",R.drawable.six);
//        animalsList.add(six);
//        Animals seven=new Animals("seven",R.drawable.seven);
//        animalsList.add(seven);
//        Animals eight=new Animals("eight",R.drawable.eight);
//        animalsList.add(eight);
//        Animals night=new Animals("night",R.drawable.night);
//        animalsList.add(three);
//        Animals ten=new Animals("three",R.drawable.ten);
//        animalsList.add(ten);
//    }

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

    //防止handler导致的内存溢出
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

    //异步加载
    class My_Asyntask extends AsyncTask<String,Void,List<Animals>>{

        @Override
        protected List<Animals> doInBackground(String... params) {
            return getJsonData(params[0]);//parms
        }

        @Override
        protected void onPostExecute(List<Animals> animalses) {
            super.onPostExecute(animalses);
            Animal_Adapter2 aniAdapter2=new Animal_Adapter2(Activity2.this,animalses);
            listview.setAdapter(aniAdapter2);
        }
    }

    //获取JSON数据并将之转化为所需的数据，在adapter中可以使用这些东西
    private List<Animals> getJsonData(String url){
        List<Animals> animallist=new ArrayList<>();
        try {
            String jsonString=readStream(new URL(URL).openStream());
            Log.d("lala", "getJsonData: "+jsonString);
            JSONObject jsonobject;
            Animals animals;
            try {
                jsonobject=new JSONObject(jsonString);
                JSONArray jsonarray=jsonobject.getJSONArray("data");
                for (int j=0;j<jsonarray.length();j++){
                    jsonobject=jsonarray.getJSONObject(j);
                    animals=new Animals();
                    animals.setName(jsonobject.getString("name"));
                    animals.setImageId(jsonobject.getString("picSmall"));
                    animallist.add(animals);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            Log.d("json", "getJsonData: "+jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }return animallist;
    }

    // 通过inputStream读取网页信息,我们定义一个方法用于读取输入流：
    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");// inputstream传入的为字节流，通过inputstreamreader将字节流转换为字符流
            BufferedReader br = new BufferedReader(isr);// 通过bufferedreader将输入流读取出来
            while ((line = br.readLine()) != null) {
                result += line;// 拼接到字符串result里面
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
