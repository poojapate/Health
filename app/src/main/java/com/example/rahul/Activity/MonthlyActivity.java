package com.example.rahul.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.rahul.R;

public class MonthlyActivity extends AppCompatActivity {
    
    LinearLayout card,caurd,Upi,upi1,net,banking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly);

        card= findViewById(R.id.card);
        caurd= findViewById(R.id.caurd);
        Upi= findViewById(R.id.Upi);
        upi1= findViewById(R.id.upi1);
        net= findViewById(R.id.net);
        banking= findViewById(R.id.banking);

        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (banking.getVisibility()==View.VISIBLE){
                    banking.setVisibility(View.GONE);
                }

                else {
                    banking.setVisibility(View.VISIBLE);
                }
               }
             });

        Upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (upi1.getVisibility()==View.VISIBLE){
                    upi1.setVisibility(View.GONE);
                }

                else {
                    upi1.setVisibility(View.VISIBLE);
                }
              }
            });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (caurd.getVisibility()==View.VISIBLE){
                    caurd.setVisibility(View.GONE);
                }

                else {
                    caurd.setVisibility(View.VISIBLE);
                }
              }
           });
         }
       }