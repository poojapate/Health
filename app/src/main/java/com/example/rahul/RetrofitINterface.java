package com.example.rahul;

import com.example.rahul.Model.LoginResponseModel;
import com.example.rahul.Model.OtpResponseModel;
import com.example.rahul.Model.RegistrationModel;
import com.example.rahul.Model.TotalVidioModel;
import com.example.rahul.Model.UploadVidioModel;
import com.example.rahul.Model.planModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitINterface {

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponseModel>LoginData(@Field("mobile")String mobile);

    @FormUrlEncoded
    @POST("otp_verified")
    Call<OtpResponseModel>OtpData(@Field("otp")String otp);

    @FormUrlEncoded
    @POST("UserResgistration")
    Call<NewREgistrationModel>RegistrationData(@Field("name")String name,
                                            @Field("email")String email,
                                            @Field("gender")String gender,
                                            @Field("dob")String dob,
                                             @Field("weight")String weight,
                                            @Field("height")String height,
                                            @Field("user_id")String user_id);
    @GET("getplan")
    Call<planModel>planData();



    @GET("getvideoupload")
    Call<TotalVidioModel>TotalData();


    @Multipart
    @POST("videoupload")
    Call<UploadVidioModel>UploadData(@Part("user_id") RequestBody user_id,
                                     @Part("video_id")RequestBody video_id,
                                     @Part("videoname")RequestBody videoname,
                                     @Part("description")RequestBody description,
                                     @Part MultipartBody.Part video);

}
