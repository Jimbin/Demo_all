package com.example.jim.demo_all;

/**
 * Created by Jim斌 on 2017/2/11.
 */

public class Animals {
    private String name;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int imageId;
    public Animals(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
}
