package com.example.rahul;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClint {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {

            return retrofit = new Retrofit.Builder().
                    baseUrl("https://inmortaltech.com/fitness-Apis/").
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
