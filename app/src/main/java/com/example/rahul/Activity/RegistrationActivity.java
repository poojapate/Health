package com.example.rahul.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.Model.RegistrationModel;
import com.example.rahul.Model.planModel;
import com.example.rahul.NewREgistrationModel;
import com.example.rahul.R;
import com.example.rahul.RetrofitClint;
import com.example.rahul.RetrofitINterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    EditText date0fbirth, weight, gender, hight, userid;
    TextView nextbutton;
    String Name,dob,Gender,wgt,hgt,user;
    final Calendar myCalendar = Calendar.getInstance();
    String EmailAdress;
    LinearLayout lay1, lay2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        date0fbirth = findViewById(R.id.date0fbirth);
        nextbutton = findViewById(R.id.nextbutton);
        weight = findViewById(R.id.weight);
        hight = findViewById(R.id.hight);
        userid = findViewById(R.id.userid);
        gender = findViewById(R.id.gender);
        lay1 = findViewById(R.id.lay1);
        lay2 = findViewById(R.id.lay2);

        Name = getIntent().getStringExtra("name");
        EmailAdress = getIntent().getStringExtra("email");


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (userid.getText().toString().isEmpty()) {
                    userid.setError("Enter Correct ID");
                    userid.requestFocus();
                    RegistrationNewApi();
                    return;
                } else if (date0fbirth.getText().toString().isEmpty()) {
                    date0fbirth.setError("Enter your DOB");
                    date0fbirth.requestFocus();
                    return;
                } else if (gender.getText().toString().isEmpty()) {
                    gender.setError("Enter your DOB");
                    gender.requestFocus();
                    return;
                } else if (weight.getText().toString().isEmpty()) {
                    weight.setError("Enter your DOB");
                    weight.requestFocus();
                    return;

                } else if (weight.getText().toString().length() < 2) {
                    weight.setError("Required");
                    weight.requestFocus();
                    return;
                } else if (hight.getText().toString().isEmpty()) {
                    hight.setError("Required");
                    hight.requestFocus();

                    return;

                }

                else {

                    Intent intent = new Intent(RegistrationActivity.this,WorkoutActivity.class);
                    intent.putExtra("bhjj", gender.getText().toString());
                    intent.putExtra("ijuko", date0fbirth.getText().toString());
                    intent.putExtra("vjkicjh", weight.getText().toString());
                    intent.putExtra("gdmjd", hight.getText().toString());
                    intent.putExtra("abcd", userid.getText().toString());
                    intent.putExtra("name", Name);
                    intent.putExtra("email", EmailAdress);
                    startActivity(intent);
                }
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();

            }

            private void updateLabel() {

                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                date0fbirth.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        date0fbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegistrationActivity.this, R.style.MyDatePickerStyle_, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDilog();
            }

            void showOptionDilog() {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegistrationActivity.this);
                alertDialog.setTitle("AlertDialog");
                String[] items = {"Male", "Female", "Other"};
                int checkedItem = 1;

                alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                gender.setText("male");
                                dialog.dismiss();
                                break;
                            case 1:
                                gender.setText("Female");
                                dialog.dismiss();
                                break;

                                case 2:
                                gender.setText("other");
                                dialog.dismiss();
                                break;

                             }
                           }
                         });
                      AlertDialog alert = alertDialog.create();
                      alert.setCanceledOnTouchOutside(true);
                       alert.show();
                     }
                   });
                  }

    private void RegistrationNewApi() {
        RetrofitClint.getRetrofit().create(RetrofitINterface.class)
                .RegistrationData(Name,EmailAdress,Gender,dob,wgt,hgt,user).enqueue(new Callback<NewREgistrationModel>() {
            @Override
            public void onResponse(Call<NewREgistrationModel> call, Response<NewREgistrationModel> response) {

                Log.d("vjhmvhk", "sucess" + response);
                Log.d("vjhmvhk", "sucess" + response.body());

                if (response.code() == 200) {


                    if (response.isSuccessful()) {

                        Toast.makeText(RegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPreferences = getSharedPreferences("NewData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();


                        editor.putString("name", response.body().getData().getName());
                        editor.putString("email", response.body().getData().getEmail());
                        editor.putString("bhjj", response.body().getData().getGender());
                        editor.putString("ijuko", response.body().getData().getDob());
                        editor.putString("vjkicjh", response.body().getData().getWeight());
                        editor.putString("gdmjd", response.body().getData().getHeight());
                        editor.putString("abcd", response.body().getData().getUserId());


                        editor.commit();

                    }
                }
            }

            @Override
            public void onFailure(Call<NewREgistrationModel> call, Throwable t) {
                Log.d("hykiju","failed"+t.getMessage());

            }
        });
    }


    }
