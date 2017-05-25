package com.example.jim.demo_all.Bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by Jim斌 on 2017/2/24.
 */

public class Person extends BmobObject {
    private String name;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
