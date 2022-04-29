package com.example.rahul.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.Model.RegistrationModel;
import com.example.rahul.Model.planModel;
import com.example.rahul.NewREgistrationModel;
import com.example.rahul.R;
import com.example.rahul.RetrofitClint;
import com.example.rahul.RetrofitINterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubsciptionActivity extends AppCompatActivity {

      String Gender,dob,weight,hight, Name,EmailAdress,userid;
      TextView mothly,subscription,plan,anual,amaount,free,trail;
      CardView card1,card2,card3;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsciption);

        mothly=findViewById(R.id.mothly);
        plan=findViewById(R.id.plan);
        card1=findViewById(R.id.card1);
        card2=findViewById(R.id.card2);
        card3=findViewById(R.id.card3);
        anual=findViewById(R.id.anual);
        amaount=findViewById(R.id.amaount);
        free=findViewById(R.id.free);
        trail=findViewById(R.id.trail);
        subscription=findViewById(R.id.subscription);


        Name=getIntent().getStringExtra("name");
        EmailAdress=getIntent().getStringExtra("email");

        Gender=getIntent().getStringExtra("bhjj");
        dob=getIntent().getStringExtra("ijuko");
        weight=getIntent().getStringExtra("vjkicjh");
        hight=getIntent().getStringExtra("gdmjd");
        userid=getIntent().getStringExtra("abcd");

               PlanApi();



                card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                card1.setBackground(getDrawable(R.drawable.cardview));
                card2.setBackground(getDrawable(R.drawable.cardbackground));
                card3.setBackground(getDrawable(R.drawable.cardbackground));

                    }
                });

                card2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                card2.setBackground(getDrawable(R.drawable.cardview));
                card1.setBackground(getDrawable(R.drawable.cardbackground));
                card3.setBackground(getDrawable(R.drawable.cardbackground));

              }
           });

                card3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                card3.setBackground(getDrawable(R.drawable.cardview));
                card1.setBackground(getDrawable(R.drawable.cardbackground));
                card2.setBackground(getDrawable(R.drawable.cardbackground));

             }
         });

                subscription.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {

                RegistrationApI();


              }
           });

         }
         void RegistrationApI(){


         String Subscription= subscription.getText().toString();

        Log.d("TAG", "RegistrationApI1: "+Name);
        Log.d("TAG", "RegistrationApI2: "+EmailAdress);
        Log.d("TAG", "RegistrationApI3: "+Gender);
        Log.d("TAG", "RegistrationApI4: "+dob);
        Log.d("TAG", "RegistrationApI5: "+weight);
        Log.d("TAG", "RegistrationApI6: "+Subscription);
        Log.d("TAG", "RegistrationApI6: "+userid);

                         RetrofitClint.getRetrofit().create(RetrofitINterface.class)
                         .RegistrationData(Name,EmailAdress,Gender,dob,weight,hight,userid).enqueue(new Callback<NewREgistrationModel>() {
                         @Override
                         public void onResponse(Call<NewREgistrationModel> call, Response<NewREgistrationModel> response) {

                           Log.d("vjhmvhk", "sucess" + response);
                           Log.d("vjhmvhk", "sucess" + response.body());

                           if (response.code() == 200) {
                               if (response.isSuccessful()) {
                                   Toast.makeText(SubsciptionActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

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
                       void PlanApi(){
                        RetrofitClint.getRetrofit().create(RetrofitINterface.class)
                                .planData().enqueue(new Callback<planModel>() {
                            @Override
                            public void onResponse(Call<planModel> call, Response<planModel> response) {
                                Log.d("hjj","sucess"+response);
                                Log.d("bhj","sucess"+response.body());


                            if (response.body().isSuccess()){

                                plan.setText(response.body().getData().get(0).getPlan());
                                mothly.setText(response.body().getData().get(0).getAmount());
                                anual.setText(response.body().getData().get(1).getPlan());
                                amaount.setText(response.body().getData().get(1).getAmount());
                                free.setText(response.body().getData().get(2).getPlan());
                                trail.setText(response.body().getData().get(2).getAmount());

                                Toast.makeText(SubsciptionActivity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                               }
                            }

                            @Override
                            public void onFailure(Call<planModel> call, Throwable t) {
                                Log.d("ghh","failed"+t.getMessage());
                            }
                        });

                      }
                    }