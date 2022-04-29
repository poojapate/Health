package com.example.rahul.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rahul.R;


public class ProfileFragment extends Fragment {

    TextView Fname,number,email,gender,Dob,wgt,hgt,subs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);


        SharedPreferences sharedPreferences= getContext().getSharedPreferences("NewData", Context.MODE_PRIVATE);

        String Name=sharedPreferences.getString("name","");
        String Email=sharedPreferences.getString("email","");
        String Gender=sharedPreferences.getString("bhjj","");
        String Weight=sharedPreferences.getString("vjkicjh","");
        String Height=sharedPreferences.getString("gdmjd","");
        String Subcription=sharedPreferences.getString("Monthly","");

        Log.d("pooja","sucsessful"+Name);


        Fname = view.findViewById(R.id.Fname);
        number = view.findViewById(R.id.number);
        email = view.findViewById(R.id.email);
        gender = view.findViewById(R.id.gender);
        wgt = view.findViewById(R.id.wgt);
        hgt= view.findViewById(R.id.hgt);
        subs = view.findViewById(R.id.subs);



        Fname.setText(Name);
        email .setText(Email);
        gender.setText(Gender);
        wgt .setText(Weight);
        hgt.setText(Height);
        subs .setText(Subcription);






        return view;
    }
}