package com.example.app.data.network;

import com.example.app.data.source.ProductApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductController {

    private static final String BASE_URL = "https://base.url.com/";

    private static ProductController INSTANCE;

    private ProductController(){

    }

    public static synchronized ProductController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductController();
        }
        return INSTANCE;
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ProductApi getProductApi() {
        return retrofit.create(ProductApi.class);
    }
}
