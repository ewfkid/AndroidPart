package com.example.app.data.network;

import com.example.app.data.source.PlaceApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaceController {

    private static final String BASE_URL = "https://base.url.com/";

    private static PlaceController INSTANCE;

    private PlaceController(){

    }

    public static synchronized PlaceController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlaceController();
        }
        return INSTANCE;
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public PlaceApi getPlaceApi() {
        return retrofit.create(PlaceApi.class);
    }
}
