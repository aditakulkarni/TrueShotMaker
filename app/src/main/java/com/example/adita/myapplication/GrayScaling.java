package com.example.adita.myapplication;

import android.graphics.Bitmap;
import android.renderscript.RSRuntimeException;
import android.support.v8.renderscript.RenderScript;


/**
 * Created by Akshay on 5/5/2017.
 */

public class GrayScaling extends SuperProcessor{

    @Override
    public Bitmap process(Bitmap input) throws RSRuntimeException {
        Bitmap output = Bitmap.createBitmap(input.getWidth(), input.getHeight(), input.getConfig());
        //Allocation object to hold parameters of input bitmap
        android.support.v8.renderscript.Allocation inputAllocation = android.support.v8.renderscript.Allocation.createFromBitmap(mRenderScript, input);
        //Allocation object to hold output bitmap
        android.support.v8.renderscript.Allocation outputAllocation = android.support.v8.renderscript.Allocation.createFromBitmap(mRenderScript, output);
        //class created my renderscript in build folder
        ScriptC_grayscale mGrayScaleScript = new ScriptC_grayscale(mRenderScript);

        //renderscript implemented on GPU using parallel function (foreach loop)
        mGrayScaleScript.forEach_root(inputAllocation, outputAllocation);
        outputAllocation.copyTo(output);

        return output;
    }

    public GrayScaling(RenderScript renderscript) {
        super(renderscript);
    }
}
