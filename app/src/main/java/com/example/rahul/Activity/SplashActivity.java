package com.example.rahul.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.rahul.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

//        SharedPreferences sharedPreferences=getSharedPreferences("NewData", Context.MODE_PRIVATE);
//        String UserId=sharedPreferences.getString("abcd","");
//        Log.d("bjcnc", "nkcmc"+UserId);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, StartActivity.class);
                startActivity(intent);
                finish();

//                    if (UserId==null){
//
//                        Intent intent = new Intent(SplashActivity.this, StartActivity.class);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                    else {
//
//                        Intent intent = new Intent(SplashActivity.this, WorkoutActivity.class);
//                          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                          startActivity(intent);
//                          finish();
//
//                    }
                   }
                 }, 2000);
               }
             }