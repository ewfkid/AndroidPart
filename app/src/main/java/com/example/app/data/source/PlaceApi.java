package com.example.app.data.source;

import com.example.app.domain.entities.PlaceEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceApi {
    @GET("v1/places/")
    Call<PlaceEntity> getPlaceDetails(
            @Query("classOfSort") String barcode,
            @Query("key") String apiKey
    );
}
