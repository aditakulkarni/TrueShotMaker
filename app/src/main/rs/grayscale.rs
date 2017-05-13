#pragma version(1)
#pragma rs java_package_name(com.example.adita.myapplication)

#define MSG_TAG "Grayscale"

void root(const uchar4 *v_in, uchar4 *v_out) {
	float4 f4 = rsUnpackColor8888(*v_in);
    //calculates avergae of RGB pixel
	float average = (f4.r + f4.g + f4.b) / 3;
	float3 output = {average, average, average};
	//this function converts it in grayscale for each pixel
	*v_out = rsPackColorTo8888(output);
}

void init(){
	rsDebug("Called init", rsUptimeMillis());
}