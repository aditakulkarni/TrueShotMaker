package com.example.adita.myapplication;

/**
 * Created by Akshay on 5/5/2017.
 */
import android.graphics.Bitmap;
import android.renderscript.RSRuntimeException;
import android.support.v8.renderscript.RenderScript;


public abstract class SuperProcessor {
    //Super class for renderscript. provides initialisation for renderscript
        protected RenderScript mRenderScript;

        public abstract Bitmap process(Bitmap input) throws RSRuntimeException;

        public SuperProcessor(RenderScript renderscript) {
            this.mRenderScript = renderscript;
        }
 }

