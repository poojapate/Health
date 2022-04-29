package com.example.rahul.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rahul.Activity.LoginActivity;
import com.example.rahul.Activity.SubsciptionActivity;
import com.example.rahul.R;


public class MoreFragment extends Fragment {

    TextView logout,contact,sub;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_more, container, false);

        logout=view.findViewById(R.id.logout);
        contact=view.findViewById(R.id.contact);
        sub=view.findViewById(R.id.sub);


        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDial =new Intent(Intent.ACTION_DIAL);
                iDial.setData(Uri.parse("tel: +918888887777"));
                startActivity(iDial);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),SubsciptionActivity.class);
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}