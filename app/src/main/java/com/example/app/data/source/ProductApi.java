package com.example.app.data.source;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductApi {
    @GET("v1/products/")
    Call<Void> getProductDetails(
            @Query("barcode") String barcode,
            @Query("fields") String fields,
            @Query("key") String apiKey
    );
}
