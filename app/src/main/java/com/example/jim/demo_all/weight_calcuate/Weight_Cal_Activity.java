package com.example.jim.demo_all.weight_calcuate;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.jim.demo_all.R;

public class Weight_Cal_Activity extends AppCompatActivity {
    private EditText weightEditText;
    private RadioButton manCheckBox;
    private RadioButton womanCheckBox;
    private Button count;
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight__cal_);
        weightEditText= (EditText) findViewById(R.id.edittext_weight);
        manCheckBox= (RadioButton) findViewById(R.id.radioButton_boy);
        womanCheckBox= (RadioButton) findViewById(R.id.radioButton_girl);
        count= (Button) findViewById(R.id.button_count);
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!weightEditText.getText().toString().trim().equals("")){
                    //判断是否已选择性别
                    if(manCheckBox.isChecked()||womanCheckBox.isChecked())
                    {
                        double weight=0;
                        weight=Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer  sb=new StringBuffer();//定义一个记录计算结果的变量
                        sb.append("------------评估结果----------- \n");//
                        if(manCheckBox.isChecked())
                        {
                            sb.append("男性标准身高：");
                            double result=evaluateHeight(weight,"男");//执行运算
                            sb.append((int)result+"(厘米)");
                        }
                        if(womanCheckBox.isChecked())
                        {
                            sb.append("女性标准身高：");
                            double result=evaluateHeight(weight,"女");//执行运算
                            sb.append((int)result+"(厘米)");
                        }
                        String  result=sb.toString();
                        TextView  resultTextView=(TextView)findViewById(R.id.textView1);
                        resultTextView.setText(result);//使用setText方法设置文本内容
                    }else
                    {
                        AlertDialog.Builder dialog=new AlertDialog.Builder(Weight_Cal_Activity.this);
                        dialog.setTitle("错误");
                        dialog.setMessage("请选择性别");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();

                        //showMessage(“请选择性别！”); // 用AlertDialog实现函数
                    }
                }else
                {
                    AlertDialog.Builder dialog=new AlertDialog.Builder(Weight_Cal_Activity.this);
                    dialog.setTitle("错误");
                    dialog.setMessage("请输入体重");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                    //showMessage(“请输入体重！”); // 用AlertDialog实现函数

                }

            }
        });
    }

    private double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="男"){
            height =170-(62- weight)/0.6;
        }else{
            height=158-(52- weight)/0.5;
        }
        return height;
    }

   /* public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu); 第一种使用方式
        // 设置菜单选项一般最好设置低于 6个选项
        MenuItem menuItem = menu.add(1001, 100, 1, "close");
        // menuItem.setIcon(R.drawable.ic_launcher); //高版本中不建议添加图标。添加了图标也不会显示的。

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 100:
                finish();
                //Toast.makeText(MainActivity.this, "点击菜单一选项", 3).show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
*/
}
