package com.example.adita.myapplication;

/**
 * Created by Akshay on 5/5/2017.
 */
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.Script;
import android.renderscript.ScriptC;
import android.renderscript.Type;
import android.content.res.Resources;

class ScriptC_grayscale extends ScriptC {
        private static final String __rs_resource_name = "grayscale";
        // Constructor
        public  ScriptC_grayscale(RenderScript rs) {
            this(rs,
                    rs.getApplicationContext().getResources(),
                    rs.getApplicationContext().getResources().getIdentifier(
                            __rs_resource_name, "raw",
                            rs.getApplicationContext().getPackageName()));
        }

        public  ScriptC_grayscale(RenderScript rs, Resources resources, int id) {
            super(rs, resources, id);
            __U8_4 = Element.U8_4(rs);
        }

        private Element __U8_4;
        private final static int mExportForEachIdx_root = 0;
        public Script.KernelID getKernelID_root() {
            return createKernelID(mExportForEachIdx_root, 3, null, null);
        }

        public void forEach_root(Allocation ain, Allocation aout) {
            // check ain
            if (!ain.getType().getElement().isCompatible(__U8_4)) {
                throw new RSRuntimeException("Type mismatch with U8_4!");
            }
            // check aout
            if (!aout.getType().getElement().isCompatible(__U8_4)) {
                throw new RSRuntimeException("Type mismatch with U8_4!");
            }
            // Verify dimensions
            Type tIn = ain.getType();
            Type tOut = aout.getType();
            if ((tIn.getCount() != tOut.getCount()) ||
                    (tIn.getX() != tOut.getX()) ||
                    (tIn.getY() != tOut.getY()) ||
                    (tIn.getZ() != tOut.getZ()) ||
                    (tIn.hasFaces() != tOut.hasFaces()) ||
                    (tIn.hasMipmaps() != tOut.hasMipmaps())) {
                throw new RSRuntimeException("Dimension mismatch between input and output parameters!");
            }
            forEach(mExportForEachIdx_root, ain, aout, null);
        }

}



