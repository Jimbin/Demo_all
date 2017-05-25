package com.example.jim.demo_all;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Spinner_Activity extends AppCompatActivity {

    //private String[] citise = { "广州市", "珠海市", "潮州市", "汕头市" };
    private List<String> all;
    private TextView city;
    private EditText et;
    private Spinner spinner;
    private Button add, delete;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_);

        String []cityname=getResources().getStringArray(R.array.cities_array);

        city= (TextView) findViewById(R.id.tv_cityname_id);
        spinner = (Spinner) findViewById(R.id.spinner_city_id);
        add= (Button) findViewById(R.id.btn_addcity_id);
        et= (EditText) findViewById(R.id.et_city_id);
        delete= (Button) findViewById(R.id.btn_delcity_id);
        all = new ArrayList<String>();
        for (int i = 0; i < cityname.length; i++)
        {
            all.add(cityname[i]);
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, all);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String newString = et.getText().toString();

                //如果输入的城市已经存在，则不再输入
                for (int i = 0; i < adapter.getCount(); i++)
                {
                    if (newString.equals(adapter.getItem(i)))
                        return;
                }

                if (!newString.equals(""))
                {
                    //将新值添加到adapter中
                    adapter.add(newString);
                    /*取得添加的值的位置*/
                    int position = adapter.getPosition(newString);
                    /*将spinner定位到添加值的位置*/
                    spinner.setSelection(position);
                    et.setText("");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                /*如果spinner非空*/
                if (spinner.getSelectedItem() != null)
                {
                    adapter.remove(spinner.getSelectedItem().toString());
                    et.setText("");

                    if (adapter.getCount() == 0)
                    {
                        city.setText("");
                    }
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            //将textview中这信息变为选择的内容
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3)
            {
                city.setText(arg0.getSelectedItem().toString());
            }

            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });

    }
}
