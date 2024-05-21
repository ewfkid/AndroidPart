package com.example.app.network;

import com.example.app.data.source.PointApi;
import com.example.app.data.source.UserApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static RetrofitFactory INSTANCE;

    private RetrofitFactory() {
    }

    public static synchronized RetrofitFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitFactory();
        }
        return INSTANCE;
    }


    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public UserApi getUserApi() {
        return retrofit.create(UserApi.class);
    }

    public PointApi getPointApi() {
        return retrofit.create(PointApi.class);
    }
}
