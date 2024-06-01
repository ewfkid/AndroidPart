package com.example.app.data.network;

import com.example.app.data.source.ProductApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductController {

    private static final String BASE_URL = "https://api.barcodelookup.com/";

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
            .baseUrl("https://api.barcodelookup.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ProductApi getProductApi() {
        return retrofit.create(ProductApi.class);
    }
}
