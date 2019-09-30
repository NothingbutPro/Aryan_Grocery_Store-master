package com.aryanonline;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.aryanonline.util.Session_management;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class SplashActivity extends AppCompatActivity {

    TextView app_name;
    ImageView imageView2;

    public static final int MY_PERMISSIONS_REQUEST_WRITE_FIELS = 102;
    private AlertDialog dialog;
    private static final int REQUEST_CODE_PERMISSION = 2;

    private Session_management sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        // app_name = (TextView)findViewById(R.id.app_name);
        imageView2 = (ImageView) findViewById(R.id.imageView2);


        sessionManagement = new Session_management(SplashActivity.this);

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(2 * 1000);

                    // After 5 seconds redirect to another inten
                    checkPermissions();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // start thread
        background.start();
    }

    /* public void checkAppPermissions() {
     *//*  if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_NETWORK_STATE)
                        != PackageManager.PERMISSION_GRANTED
                )*//*
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_NETWORK_STATE)) {
                go_next();
            } else {

                go_next();
        }
    }*/

    private void checkPermissions() {
//        go_next();
        if (!(ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);

        }
//        else
//
//            if (!(ActivityCompat.checkSelfPermission(this, ACCESS_NETWORK_STATE) == PERMISSION_GRANTED)) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{ACCESS_NETWORK_STATE}, REQUEST_CODE_PERMISSION);
//
//        }
        else {


            go_next();

        }


    }

    public void go_next() {

        if (sessionManagement.isLoggedIn()) {

            Intent startmain = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(startmain);
            finish();
        } else {
            Intent startmain = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(startmain);
            finish();
        }



}


  /*  @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_FIELS) {
            if (grantResults.length > 0
                    && grantResults[0] == PERMISSION_GRANTED) {
                go_next();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setMessage("App required some permission please enable it")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                                openPermissionScreen();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        });
                dialog = builder.show();
            }
            return;
        }
    }

    public void go_next() {

           if(sessionManagement.isLoggedIn()) {

            Intent startmain = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(startmain);
            finish();
        }
        else{
            Intent startmain = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(startmain);
            finish();
        }


       *//* if(sessionManagement.isLoggedIn()) {

            sessionManagement.checkLogin();


        }else{
            Intent startmain = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(startmain);
        }

        finish();*/


    public void openPermissionScreen() {

        // startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", SplashActivity.this.getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkPermissions();
    }
}