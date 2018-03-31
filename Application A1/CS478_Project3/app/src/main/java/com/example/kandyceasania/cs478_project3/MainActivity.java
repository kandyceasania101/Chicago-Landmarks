package com.example.kandyceasania.cs478_project3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;



public class MainActivity extends Activity {
    Button button1, button2;
    private static String app_permission = "edu.uic.cs478.sp18.project3";
    private static String intentActionR1 = "Calling All Receivers 1";
    private static String intentActionR2 = "Calling All Receivers 2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set-up button 1 for attractions
        button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntentAndBroadcast(intentActionR1);
            }
        });

        //set-up button 2 for restaurants
        button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntentAndBroadcast(intentActionR2);
            }
        });
    }

    //checks for permission then set intent action and broadcast
    private void setIntentAndBroadcast(String action) {
        if (ContextCompat.checkSelfPermission(this, app_permission)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(action);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendBroadcast(intent);
        }else {
            ActivityCompat.requestPermissions(this, new String[]{app_permission}, 0) ;
        }

    }
}