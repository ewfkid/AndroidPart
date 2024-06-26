package com.example.app.data.source;

import com.example.app.data.dto.AccountDto;
import com.example.app.data.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {
    @GET("/user/{id}")
    Call<UserDto> getById(@Path("id") String id);

    @GET("/user/{email}")
    Call<Void> isExist(@Path("email") String login);

    @POST("/user/register")
    Call<Void> register(@Body AccountDto dto);

    @GET("/user/login")
    Call<Void> login();

    @PUT("/user/{id}")
    Call<UserDto> update(@Path("id") String id, @Body UserDto dto);
}
