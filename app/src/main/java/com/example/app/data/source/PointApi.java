package com.example.app.data.source;

import com.example.app.data.dto.PointDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PointApi {

    @GET("point/{user_id}")
    Call<List<PointDto>> getByUserId(@Path("user_id") String userId);

    @GET("point/{id}")
    Call<PointDto> getById(@Path("id") String id);

    @POST("point")
    Call<PointDto> createPoint(@Body PointDto dto);

    @DELETE("point/{id}")
    Call<Void> deleteById(@Path("id") String id);

    @PUT("point/{id}")
    Call<PointDto> update(@Path("id") String id, @Body String name);

    @GET("point/{name}")
    Call<PointDto> getByName(@Path("name") String name);
}

