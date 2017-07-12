package com.example.jim.demo_all.present;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jim斌 on 2017/7/12.
 */
public class ImageLoder {
    private ImageView mimageView;

    private android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mimageView.setImageBitmap((Bitmap) msg.obj);
//            mimageView.setImageBitmap((Bitmap) msg.obj);
            Log.d("jjjjjj", "handleMessage: "+msg.obj);
        }
    };
    public void showImageByThread(ImageView imageView, final String url){
        mimageView=imageView;
        Log.d("aaa", "showImageByThread: "+imageView);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    Bitmap bitmap=getBitmapFromUrl(url);
                    Message message=Message.obtain();
                    message.obj=bitmap;
                    handler.sendMessage(message);//把bitmap封装成message的obj发送过去
                }
            }.start();
    }
    public Bitmap getBitmapFromUrl(String urlString){
        Bitmap bitmap;
        InputStream inputStream = null;
        try {
            URL url=new URL(urlString);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            inputStream=new BufferedInputStream(connection.getInputStream());
            bitmap= BitmapFactory.decodeStream(inputStream);//安卓自带接口可以直接将inputStream转成bitmap
            return bitmap;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();//注意资源的释放，避免OOM
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
