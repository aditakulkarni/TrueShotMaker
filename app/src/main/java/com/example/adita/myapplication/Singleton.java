package com.example.adita.myapplication;

import java.util.ArrayList;

/**
 * Created by Akshay on 5/12/2017.
 */

public class Singleton {

    private ArrayList<Image> arrayList;

    private static Singleton instance;

    private Singleton(){
        arrayList = new ArrayList<Image>();
    }

    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public ArrayList<Image> getArrayList() {
        return arrayList;
    }
}
