package com.example.app.data.source;

import com.example.app.data.dto.AccountDto;
import com.example.app.data.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @GET("user/{id}")
    Call<UserDto> getById(@Path("id") String id);
    Call<Void> isExist(@Path("username") String login);
    @POST("edu/v1/user/register")
    Call<Void> register(@Body AccountDto dto);
    @GET("edu/v1/user/login")
    Call<Void> login();
}
