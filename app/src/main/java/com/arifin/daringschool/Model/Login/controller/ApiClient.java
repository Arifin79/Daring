package com.arifin.daringschool.Model.Login.controller;

import com.arifin.daringschool.Model.Login.controller.LoginInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://localhost:3000/")
                .client(okHttpClient)
                .build();

        return retrofit;

    }

    public static LoginInterface getService(){
        LoginInterface loginInterface = getRetrofit().create(LoginInterface.class);

        return loginInterface;
    }

}
