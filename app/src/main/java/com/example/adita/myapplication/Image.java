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
    Bitmap faceimage;

    public Image(){

    }
    public Image(Image temp){
        this.count = temp.getCount();
        this.name = temp.getName();
        this.imgBitmap = temp.getImgBitmap();
        this.faceimage = temp.getFaceimage();
    }
    public Bitmap getFaceimage() {
        return faceimage;
    }

    public void setFaceimage(Bitmap faceimage) {
        this.faceimage = faceimage;
    }

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

    //resize the image to maxsize
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
