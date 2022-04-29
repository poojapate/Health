package com.example.rahul.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.R;

import java.util.regex.Pattern;

public class DetailsActivity extends AppCompatActivity {

       TextView next;
       EditText  name,emailAdress;
       CheckBox chek;
       String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        next=findViewById(R.id.next);
        name=findViewById(R.id.name);
            chek=findViewById(R.id.chek);
        emailAdress=findViewById(R.id.emailAdress);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()){

                    name.setError("Name Required");
                    name.requestFocus();
                    return;
                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(emailAdress.getText().toString()).matches()){
                    emailAdress.setError("Invailid Email Address");
                    emailAdress.requestFocus();
                    return;

                } else if ( !chek.isChecked()) {
                    Toast.makeText(DetailsActivity.this, "pls clicked ChekBox", Toast.LENGTH_SHORT).show();


                } else {
                    Intent intent = new Intent(DetailsActivity.this, RegistrationActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("email", emailAdress.getText().toString());
                    startActivity(intent);

                }
                 }
              });
             }
            }