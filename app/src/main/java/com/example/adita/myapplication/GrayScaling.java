package com.example.adita.myapplication;

import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.RSRuntimeException;
//import android.renderscript.RenderScript.*;
import android.support.v8.renderscript.RenderScript;


/**
 * Created by Akshay on 5/5/2017.
 */

public class GrayScaling extends SuperProcessor{

    @Override
    public Bitmap process(Bitmap input) throws RSRuntimeException {
        Bitmap output = Bitmap.createBitmap(input.getWidth(), input.getHeight(), input.getConfig());
        android.support.v8.renderscript.Allocation inputAllocation = android.support.v8.renderscript.Allocation.createFromBitmap(mRenderScript, input);
        android.support.v8.renderscript.Allocation outputAllocation = android.support.v8.renderscript.Allocation.createFromBitmap(mRenderScript, output);
        ScriptC_grayscale mGrayScaleScript = new ScriptC_grayscale(mRenderScript);

        mGrayScaleScript.forEach_root(inputAllocation, outputAllocation);
        outputAllocation.copyTo(output);

        return output;
    }

    public GrayScaling(RenderScript renderscript) {
        super(renderscript);
    }
}
