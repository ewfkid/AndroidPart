package com.example.app.data.source;

import com.example.app.data.dto.PointDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PointApi {

    @GET("point/{id}")
    Call<PointDto> getById(@Path("id") String id);
}

