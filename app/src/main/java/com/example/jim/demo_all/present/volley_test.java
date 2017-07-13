package com.example.jim.demo_all.present;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Jim斌 on 2017/7/13.
 */

public class volley_test {
    public com.android.volley.toolbox.StringRequest stringRequest;
    public volley_test(){
        Get();
    }
//    StringRequest stringRequest = new StringRequest("http://www.baidu.com",
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.d("TAG", response);
//                }
//            }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            Log.e("TAG", error.getMessage(), error);
//        }
//    });
    public void Get() {
        stringRequest=new com.android.volley.toolbox.StringRequest(
                "https://www.baidu.com",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("hahahahTAG", response);
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("TAG", error.getMessage(), error);
        }
    });
    }
    public void Post() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "https://m.weather.com.cn/data/101010100.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
    }
}
