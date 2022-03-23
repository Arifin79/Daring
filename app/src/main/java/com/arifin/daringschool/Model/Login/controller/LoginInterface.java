package com.arifin.daringschool.Model.Login.controller;

import com.arifin.daringschool.Model.Login.model.Login;
import com.arifin.daringschool.Model.Login.model.ResponseLogin;

import java.util.HashMap;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginInterface {

    @FormUrlEncoded
    @POST("user/signin")
    @Headers("Accept: application/json")
    Call<ResponseLogin> loginUser(@Body ResponseLogin responseLogin);
}
