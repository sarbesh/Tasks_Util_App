package com.sarbesh.tasksutil.service;

import com.sarbesh.tasksutil.data.model.LoginRequest;
import com.sarbesh.tasksutil.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LoginService {

    @POST("accounts/api/token/")
    Call<LoginResponse> getAuthToken(@Body LoginRequest loginRequest);

    @POST("accounts/api/token/refresh/")
    Call<LoginResponse> refreshAuthToken(@Body LoginResponse refreshRequest);
}
