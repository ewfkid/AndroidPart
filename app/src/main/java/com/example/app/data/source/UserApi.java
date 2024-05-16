package com.example.app.data.source;

import com.example.app.data.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {

    @GET("user/{id}")
    Call<UserDto> getById(@Path("id") String id);
}
