package com.example.adita.myapplication;

import java.io.Serializable;

/**
 * Created by Adita on 4/29/2017.
 */

public class Image implements Serializable {
    private
    byte[] image;
    int id;

    public Image(byte[] image, int id){
        this.image = image;
        this.id = id;
    }

    public void setImage(byte[] Image, int id){
        this.image = Image;
        this.id = id;
    }

    public byte[] getImage(){
        return image;
    }

}
