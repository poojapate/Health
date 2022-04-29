package com.example.rahul.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.Model.LoginResponseModel;
import com.example.rahul.R;
import com.example.rahul.RetrofitClint;
import com.example.rahul.RetrofitINterface;
import com.hbb20.CountryCodePicker;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

        TextView otp;
        EditText number;
        String mobilePattern= "^[6-9][0-9]{9}$";
        CountryCodePicker ccp;
        String ContryCode;
        ProgressBar simpleProgressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            otp=findViewById(R.id.otp);
            number=findViewById(R.id.number);
            ccp=findViewById(R.id.ccp);
            simpleProgressBar=findViewById(R.id.simpleProgressBar);

            ccp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContryCode = ccp.getSelectedCountryCodeWithPlus();

                }
            });

            otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   simpleProgressBar.setVisibility(View.VISIBLE);

                   if (number.getText().toString().matches(mobilePattern)==false) {
                    number.setError("Invalid Mobile Number");
                    number.requestFocus();
                    Log.d("mia","sucess");

                    }

                    else {
                    Log.d("kjhfdsiuhiug", "onClick: "+number.getText().toString());
                        loginapi(number.getText().toString());
                      }
                    }
                  });
                 }

                void loginapi(String number){
                    Log.d("kjhfdsiuhiug2", "onClick: "+number);

                RetrofitClint.getRetrofit().create(RetrofitINterface.class)
               .LoginData(number).enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                Log.d("vhkmj","sucess"+response.body());

                    if (response.code()==200){

                    if (response.body().isSuccess())
                    {
                       Toast.makeText(LoginActivity.this,response.body().getOtp(), Toast.LENGTH_SHORT).show();


                       Intent intent=new Intent(LoginActivity.this,OtpActivity.class);
                       intent.putExtra("otp",response.body().getOtp());
                       intent.putExtra("Number",number);
                       intent.putExtra("code",ccp.getSelectedCountryCode().toString());
                       Log.d("gnnm","sss"+ccp.getSelectedCountryCode());
                       startActivity(intent);

                    }

                   }
                 }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    Log.d("jjkbjjj","failed"+t.getMessage());

                  }
               });
             }
           }