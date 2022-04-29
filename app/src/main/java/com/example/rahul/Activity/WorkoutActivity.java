package com.example.rahul.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.rahul.Fragment.ExpertVidioFragment;
import com.example.rahul.Fragment.MoreFragment;
import com.example.rahul.Fragment.SelfVidioFragment;
import com.example.rahul.ProfileFragment;
import com.example.rahul.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WorkoutActivity extends AppCompatActivity {

        FrameLayout fram;
        BottomNavigationView bottomnav;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);





            fram=findViewById(R.id.fram);
            bottomnav=findViewById(R.id.bottomnav);

            replace(new ExpertVidioFragment());


        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.Expert:
                        replace(new ExpertVidioFragment());
                        break;
                    case R.id.selfvidio:
                        replace(new SelfVidioFragment());
                        break;
                    case R.id.profile:
                        replace(new ProfileFragment());
                        break;
                    case R.id.More:
                        replace(new MoreFragment());
                        break;

                }
                return true;
            }

         });
       }
       void replace(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fram, fragment);
        ft.commit();
      }
     }