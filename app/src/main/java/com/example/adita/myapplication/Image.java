package com.example.adita.myapplication;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Adita on 4/29/2017.
 */

public class Image {
    int count=0;
    Bitmap imgBitmap;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return this.count;
    }

    public void incrementCount() {
        this.count++;
    }

    public void decrementCount() {
        this.count--;
    }

    public Bitmap getImgBitmap() {
        return imgBitmap;
    }

    public void setImgBitmap(Bitmap imgBitmap) {
        this.imgBitmap = imgBitmap;
    }
}
