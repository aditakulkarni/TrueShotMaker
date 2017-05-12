package com.example.adita.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraDemo extends Activity {
	private static final String TAG = "FrontCamera";
	Camera camera;
	Preview preview;
	Button buttonClick;
	Button button2;
    int count = 0;
	int stillCount = 0;
	boolean success = true;
    //Image arr[] = new Image[10];
	int numberOfCaptures = 10;
	RadioButton button1, button5, button10;

	File folder = new File(Environment.getExternalStorageDirectory() + "/EmergingData");

    private static final int REQUEST_PERMISSIONS = 20;

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button10 = (RadioButton) findViewById(R.id.radioButton3);

		button10.setChecked(true);

		if (ContextCompat.checkSelfPermission(CameraDemo.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(CameraDemo.this,
                        Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(CameraDemo.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(CameraDemo.this,
                    Manifest.permission.CAMERA)) {

                Snackbar.make(findViewById(android.R.id.content),
                        "Please Grant Permissions",
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(CameraDemo.this,
                                        new String[]{Manifest.permission
                                                .WRITE_EXTERNAL_STORAGE,Manifest.permission
                                                .CAMERA},
                                        REQUEST_PERMISSIONS);
                            }
                        }).show();
            } else {

                ActivityCompat.requestPermissions(CameraDemo.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission
                                .CAMERA},
                        REQUEST_PERMISSIONS);

            }
        }else{
            Log.d(TAG, "Inside Else part --> Permission already granted");
            createImagesFolderAndStartCamera();
        }
	}

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        Log.d(TAG,"Inside onRequestPermissionsResult function");
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if ((grantResults.length > 0) && (grantResults[0]+grantResults[1]) == PackageManager.PERMISSION_GRANTED) {
                    //Call whatever you want
                    Toast.makeText(this, "Write Storage permission granted", Toast.LENGTH_SHORT).show();
                    // myMethod();
                    createImagesFolderAndStartCamera();
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Enable Permissions from settings",
                            Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                                    intent.setData(Uri.parse("package:" + getPackageName()));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                    startActivity(intent);
                                }
                            }).show();
                }
                return;
            }
        }
    }

    public void createImagesFolderAndStartCamera(){
		preview = new Preview(this);
		((FrameLayout) findViewById(R.id.preview)).addView(preview);

		button1 = (RadioButton) findViewById(R.id.radioButton);
		button5 = (RadioButton) findViewById(R.id.radioButton2);

		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				numberOfCaptures = 1;
				if(button5.isChecked()) {
					button5.setChecked(false);
				}
				if(button10.isChecked()) {
					button10.setChecked(false);
				}
			}
		});

		button5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				numberOfCaptures = 5;
				if(button1.isChecked()) {
					button1.setChecked(false);
				}
				if(button10.isChecked()) {
					button10.setChecked(false);
				}
			}
		});

		button10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				numberOfCaptures = 10;
				if(button1.isChecked()) {
					button1.setChecked(false);
				}
				if(button5.isChecked()) {
					button5.setChecked(false);
				}
			}
		});

		if (!folder.exists()) {
			success = folder.mkdir();
		}
		buttonClick = (Button) findViewById(R.id.buttonClick);

		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				preview.camera.takePicture(shutterCallback, rawCallback,
						jpegCallback);
				buttonClick.setEnabled(false);


			}
		});
	}


		public  void OpenFDActivity(View view){
			Intent myIntent = new Intent(CameraDemo.this,FdActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("numberOfCaptures",numberOfCaptures);
			//bundle.putString("path",folder.getAbsolutePath());
			myIntent.putExtras(bundle);
			startActivity(myIntent);
		}


	@Override
	protected void onPause() {
		super.onPause();
		if (camera != null) {
			camera.release();
		}
	}

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			Log.d(TAG, "onShutter'd");
		}
	};

	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "onPictureTaken - raw with data = " + ((data != null) ? data.length : " NULL"));
		}
	};

	PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			FileOutputStream outStream = null;
			Bitmap img = null;
			try {
				outStream = new FileOutputStream(String.format(
						folder.getAbsolutePath()+"/still%d.bmp", count));

				Image tempimg = new Image();
				img = BitmapFactory.decodeByteArray(data,0,data.length);
				tempimg.setImgBitmap(img);
				tempimg.setName("still" + count + ".bmp");
				Singleton.getInstance().getArrayList().add(tempimg);
				Log.d(TAG,"tis  "+ Singleton.getInstance().getArrayList().get(count).getName());
				count++;
				outStream.write(data);
				outStream.close();
				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
			Log.d(TAG, "onPictureTaken - jpeg");
			Log.d(TAG,Singleton.getInstance().getArrayList().get(stillCount).getName());
			Log.d(TAG,"Length = :"+Singleton.getInstance().getArrayList().size());

			try {
				stillCount++;
				camera.startPreview();
				if (stillCount < numberOfCaptures) {
					preview.camera.takePicture(shutterCallback, rawCallback,
							jpegCallback);
				} else {
					stillCount = 0;
					buttonClick.setEnabled(true);
				}
			} catch (Exception e) {
				Log.d(TAG, "Error starting preview: " + e.toString());
			}
		}
	};

}
