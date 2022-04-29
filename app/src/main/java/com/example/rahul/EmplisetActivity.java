package com.example.rahul;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EmplisetActivity extends AppCompatActivity {
    private final int CAMERA_REQ_CODE=100;


    TextView share,email,message,call,camara;
    ImageView imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empliset);

        call=findViewById(R.id.call);
        message=findViewById(R.id.message);
        email=findViewById(R.id.email);
        share=findViewById(R.id.share);
        camara=findViewById(R.id.camara);
        imgview=findViewById(R.id.imgview);




        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iShare=new Intent(Intent.ACTION_SEND);
                iShare.setType("text/plain");
                iShare.putExtra(Intent.EXTRA_TEXT,"U7ON2T3WH57MMMMARVQHT5IOLB7ZDXOHFXAUEB3CQRDA66KLDUX3RXPE5E");
                startActivity(Intent.createChooser(iShare,"share via"));

            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iEmail=new Intent(Intent.ACTION_SEND);
                iEmail.setType("message/rfc822");
                iEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"abc@gmail.com","xyz.@gmail.com"});
                iEmail.putExtra(Intent.EXTRA_SUBJECT,"Queries");
                iEmail.putExtra(Intent.EXTRA_TEXT,"please Resolve this issue asap");
                 startActivity(Intent.createChooser(iEmail,"EmailVia"));

            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent iMgs= new Intent(Intent.ACTION_SENDTO);
             iMgs.setData(Uri.parse("smsto:"+Uri.encode("+918888887777")));
             iMgs.putExtra("sma_body","please solve this issue asap.");
             startActivity(iMgs);

            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iDial =new Intent(Intent.ACTION_DIAL);
                iDial.setData(Uri.parse("tel: +918888887777"));
                startActivity(iDial);

            }
        });
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCamara=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamara,CAMERA_REQ_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            if (requestCode==CAMERA_REQ_CODE){
                //for camera

                Bitmap img = (Bitmap)(data.getExtras().get("data"));
                imgview.setImageBitmap(img);


            }


        }

    }

}