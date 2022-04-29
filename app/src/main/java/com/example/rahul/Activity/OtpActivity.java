package com.example.rahul.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.rahul.Model.LoginResponseModel;
import com.example.rahul.Model.OtpResponseModel;
import com.example.rahul.R;
import com.example.rahul.RetrofitClint;
import com.example.rahul.RetrofitINterface;
import com.hbb20.CountryCodePicker;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {


    TextView mTextViewCountDown, mButtonReset, submit, text,txt1;
    public int counter;
    PinView firstPinView;
    String otp;
    String nmbr;
    String cotp;
    String ccode;
    CountryCodePicker countryCodePicker;
    private static final long START_TIME_IN_MILLIS = 59000;
    CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        submit = findViewById(R.id.submit);
        text = findViewById(R.id.text);
        txt1 = findViewById(R.id.txt1);

        mTextViewCountDown = findViewById(R.id.Timer);
        mButtonReset = findViewById(R.id.resend);



        otp = getIntent().getStringExtra("otp");
        nmbr = getIntent().getStringExtra("Number");
        ccode = getIntent().getStringExtra("code");

        Log.d("sucess", "sucess: " + nmbr);
        text.setText(nmbr);
        txt1.setText(ccode);
        Log.d("kjfehrkjf", "onCreate: " + ccode);



        if (mTimerRunning) {
            pauseTimer();
        } else {
            startTimer();
        }

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendotp();
                resetTimer();
                startTimer();
                mTextViewCountDown.setVisibility(View.VISIBLE);
            }
        });
        updateCountDownText();

        firstPinView = findViewById(R.id.firstPinView);
        firstPinView.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        firstPinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 4) {
//             Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String otp1=firstPinView.getText().toString();

                if (otp.equals(otp1)){
                    firstPinView.setLineColor(Color.GRAY);
                    OtpApi();
                }
                else if (otp.equals("")||(otp.isEmpty())||(otp.length() < 4)) {
                    Toast.makeText(OtpActivity.this, "Please enter otp", Toast.LENGTH_SHORT).show();
                }

                else if ( otp!=cotp){
                    Toast.makeText(OtpActivity.this, "Wrong OTP", Toast.LENGTH_SHORT).show();

                }


            }

        });
    }

    private void sendotp() {
        RetrofitClint.getRetrofit().create(RetrofitINterface.class)
                .LoginData(nmbr).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
               Log.d("hjjjj","sucess"+response);
               Log.d("hjb","sucess"+response.body());
               if (response.isSuccessful()){
                   otp = response.body().getOtp();
                   Toast.makeText(OtpActivity.this,response.body().getOtp(), Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
               Log.d("hhcj","sucess"+t.getMessage());
            }
        });
}

    private void updateCountDownText() {

        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void resetTimer() {

        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {

        mCountDownTimer.cancel();
        mTimerRunning = false;

        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonReset.setVisibility(View.VISIBLE);
                mTextViewCountDown.setVisibility(View.INVISIBLE);
            }
        }.start();

        mTimerRunning = true;

        mButtonReset.setVisibility(View.INVISIBLE);

    }


    void OtpApi() {

        RetrofitClint.getRetrofit().create(RetrofitINterface.class).
                OtpData(firstPinView.getText().toString()).enqueue(new Callback<OtpResponseModel>() {
            @Override
            public void onResponse(Call<OtpResponseModel> call, Response<OtpResponseModel> response) {

                Log.d("dttujyh", "sucessful" + response);
                Log.d("bhjhj", "sucessful" + response.body());

                if (response.code() == 200) {

                    if (response.body().isSuccess()) ;

                    final Dialog dialog = new Dialog(OtpActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.sucessdilog);
                    TextView okk = dialog.findViewById(R.id.okk);

                    okk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(OtpActivity.this, DetailsActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    dialog.show();
                }

            }

            @Override
            public void onFailure(Call<OtpResponseModel> call, Throwable t) {
                Log.d("ujy", "failed" + t.getMessage());
            }
        });
    }

    }
