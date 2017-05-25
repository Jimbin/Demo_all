package com.example.jim.demo_all.MyPhone;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jim.demo_all.R;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends Activity
{
    ListView lv_second;
    List<Contact> contacts;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //获取联系人信息
        contacts = getContactInfo();
        //找到相应的控件
        lv_second=(ListView) findViewById(R.id.lv_second);
        //为ListView设置适配器
        lv_second.setAdapter(new MyAdapter(this,contacts));

        //为listView设置点击事件，当点击其某一子项时，需要把该子项的内容返回到上一个activity
        lv_second.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id)
            {
                //获取子项上联系人对象
                Contact contact = (Contact) lv_second.getItemAtPosition(position);
                //获取联系人的姓名和手机号
                String content ="姓名："+contact.getName();
                String content2 =contact.getPhone();
                //创建Intent对象，来传递数据
                Intent intent = new Intent();
                intent.putExtra("content", content);
                intent.putExtra("content2",content2);
                //设置返回结果
                setResult(0,intent);

                //关闭当前的activity
                finish();
            }
        });


    }
    /**
     * 获取系统联系人信息（核心内容）
     * @return Contact集合
     */
    public  List<Contact> getContactInfo()
    {
        //创建一个list集合，里面的对象都是Contact
        List<Contact> contacts = new ArrayList<Contact>();
        //获得一个ContentResolver的实例,可通过Activity的成员方法getContentResovler()方法，用于查询数据用
        //ContentResolver参考网址http://blog.sina.com.cn/s/blog_8ce4eca801010qk5.html
        ContentResolver resolver  = getContentResolver();
        //创建两个Uri对象
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        //调用ContentResolver对象的query方法，进行数据查询 返回一个Cursor对象
        Cursor cursor = resolver.query(uri, null, null, null, null);
        //此处需要一个双层循环，来实现查找联系人姓名和号码
        //(外层循环)循环cursor 取出查找所用字段"_id"
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            Cursor dataCursor = resolver.query(dataUri, null,  "raw_contact_id = ?", new String[]{String.valueOf(id)}, null);
            Contact contact = new Contact();
            //内存循环，循环data1和mimetype两个字段内容
            while(dataCursor.moveToNext())
            {
                String data = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                String mimetype = dataCursor.getString(dataCursor.getColumnIndex("mimetype"));

                //如果mimetype是手机号，就把data数据放到contant中
                if("vnd.android.cursor.item/phone_v2".equals(mimetype))
                {
                    contact.setPhone(data);
                    //如果mimetype是姓名，就把data数据放到contant中
                }else if("vnd.android.cursor.item/name".equals(mimetype)){
                    contact.setName(data);
                }
            }
            //每循环一次就把一个contant（联系人）对象存放到contacts集合当中
            contacts.add(contact);
            //关闭dataCursor
            dataCursor.close();
        }
        //关闭cursor
        cursor.close();
        return contacts;

    }
    /**
     * 自定义adapter
     * @author YSY
     *
     */
    public class MyAdapter extends BaseAdapter
    {
        Context context;
        List<Contact> contacts;
        public MyAdapter(Context context,List<Contact> contacts)
        {
            this.contacts=contacts;
            this.context=context;
        }

        public int getCount()
        {

            return contacts.size();
        }
        public Object getItem(int position)
        {

            return contacts.get(position);
        }
        public long getItemId(int position)
        {

            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            Holder holder = null;
            if(convertView==null)
            {
                holder = new Holder();
                convertView = View.inflate(SecondActivity.this, R.layout.item, null);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);

                convertView.setTag(holder);
            }
            else
            {
                holder = (Holder)convertView.getTag();
            }
            holder.tv_name.setText(contacts.get(position).getName());
            holder.tv_phone.setText(contacts.get(position).getPhone());

            return convertView;
        }

        public class Holder
        {
            private TextView tv_name;
            private TextView tv_phone;
        }
    }

}