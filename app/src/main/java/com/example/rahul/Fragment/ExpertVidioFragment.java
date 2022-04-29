package com.example.rahul.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.rahul.Adapter.ExpertVidioAdapter;
import com.example.rahul.Model.TotalVidioModel;
import com.example.rahul.Model.UploadVidioModel;
import com.example.rahul.R;
import com.example.rahul.RetrofitClint;
import com.example.rahul.RetrofitINterface;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ExpertVidioFragment extends Fragment {

    RecyclerView recycle;
    TextView upload,mobileNo;
    int SELECT_VIDEO_REQUEST = 100;
    VideoView vidio;
    private static final int BUFFER_SIZE = 1024 * 2;
    private static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";
    String path;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_expert_vidio, container, false);


        SharedPreferences sharedPreferences=getContext().getSharedPreferences("NewData", Context.MODE_PRIVATE);
        String Name=sharedPreferences.getString("name","");
        Log.d("gyuygu","sucess"+Name);


        recycle = view.findViewById(R.id.recycle);
        upload = view.findViewById(R.id.upload);
        mobileNo = view.findViewById(R.id.mobileNo);

        mobileNo.setText(Name);



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.uploadvidio);

                ImageView close = dialog.findViewById(R.id.close);
                TextView sumit = dialog.findViewById(R.id.sumit);
                TextView selectVidio = dialog.findViewById(R.id.selectVidio);
                vidio = dialog.findViewById(R.id.vidio);

                selectVidio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent;
                        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
                            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                        } else {
                            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.INTERNAL_CONTENT_URI);
                        }

                        intent.setType("video/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.putExtra("return-data", true);
                        startActivityForResult(intent, SELECT_VIDEO_REQUEST);
                    }
                });

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                sumit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    videUpload(path);

                    }
                });
                dialog.show();
            }
        });

        UploadApi();
        return view;

    }

    void videUpload(String path){

        File file = new File(path);

        Log.d("wkdhwudhsui", "videUpload: "+file);

        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("video", file.getName(), requestBody);
        
        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody video_id = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "oats");
        RequestBody videoname = RequestBody.create(MediaType.parse("text/plain"), "chest");

        Log.d("wmhdgewudy1", "videUpload: "+user_id.toString());
        Log.d("wmhdgewudy12", "videUpload: "+video_id.toString());
        Log.d("wmhdgewudy13", "videUpload: "+description.toString());
        Log.d("wmhdgewudy14", "videUpload: "+videoname.toString());

        RetrofitClint.getRetrofit().create(RetrofitINterface.class)
                .UploadData(user_id,video_id,videoname,description,body)
                .enqueue(new Callback<UploadVidioModel>() {
                    @Override
                    public void onResponse(Call<UploadVidioModel> call, Response<UploadVidioModel> response) {
                        Log.d("jdgwukygw","sucess"+response);
                        Log.d("wkjhwiudhi","sucessful"+response.body());

                        Log.d("Bhuppppp", "onResponse: ");

                    }
                    @Override
                    public void onFailure(Call<UploadVidioModel> call, Throwable t) {
                        Log.d("wkudhidu","failed"+t.toString()) ;
                        Log.d("Bhupppp2p", "onResponse: ");
                    }
                });


    }

    void UploadApi() {
        RetrofitClint.getRetrofit().create(RetrofitINterface.class)
                .TotalData().enqueue(new Callback<TotalVidioModel>() {
            @Override
            public void onResponse(Call<TotalVidioModel> call, Response<TotalVidioModel> response) {
                Log.d("fjfjf", "sucessful" + response);
                Log.d("gdfggfdkfjhh", "sucessful" + response.body());

                if (response.code() == 200) {
                    if (response.body().isSuccess()) {

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                                LinearLayoutManager.VERTICAL, false);
                        recycle.setLayoutManager(linearLayoutManager);

                        ExpertVidioAdapter expertVidioAdapter = new ExpertVidioAdapter(getContext(), response.body().getData());
                        recycle.setAdapter(expertVidioAdapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<TotalVidioModel> call, Throwable t) {
                Log.d("fhjkk", "failed" + t.getMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_VIDEO_REQUEST) {

                Uri selectedVideoUri = data.getData();
                Log.d("kdhfjdfd", "onActivityResult: "+selectedVideoUri);
                vidio.setVideoURI(selectedVideoUri);
                vidio.start();

                 path = getFilePathFromURI(getContext(),selectedVideoUri);
                Log.d("mdjhewfueif", "onActivityResult: "+path);
                //videUpload(path);

            }

        }
    }

    public static String getFilePathFromURI(Context context, Uri contentUri) {

        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        File copyFile = new File(wallpaperDirectory + File.separator + Calendar.getInstance()
                .getTimeInMillis()+".mp4");

        copy(context, contentUri, copyFile);
        Log.d("vPath--->",copyFile.getAbsolutePath());

        return copyFile.getAbsolutePath();

    }

    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copystream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int copystream(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
            try {
                in.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
        }
        return count;
    }
}
