package com.example.jim.demo_all.Bean;

/**
 * Created by Jim斌 on 2017/2/11.
 */

public class Animals {
    private String name;
    private String picture;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String imageId;
    public Animals(String name,String imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public Animals(){};
}
