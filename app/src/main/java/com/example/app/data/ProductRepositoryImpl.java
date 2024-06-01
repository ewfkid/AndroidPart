package com.example.app.data;

import androidx.annotation.NonNull;

import com.example.app.data.network.ProductController;
import com.example.app.data.network.RetrofitFactory;
import com.example.app.data.source.ProductApi;
import com.example.app.data.utils.CallToConsumer;
import com.example.app.domain.entities.Status;
import com.example.app.domain.product.ProductRepository;

import java.util.function.Consumer;


public class ProductRepositoryImpl implements ProductRepository {
    private static ProductRepositoryImpl  INSTANCE;

    private ProductApi productApi = ProductController.getInstance().getProductApi();

    private static final String API_KEY = "f5zmhh9e4g8vgzgq8kmejjic2edow4";

    private ProductRepositoryImpl() {
    }

    public static synchronized ProductRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductRepositoryImpl();
        }
        return INSTANCE;
    }



    @Override
    public void getClassOfSort(@NonNull String barcode, @NonNull Consumer<Status<Void>> callback) {
        productApi.getProductDetails(barcode, "material", API_KEY).enqueue(new CallToConsumer<>(callback,
                dto -> null)
        );

    }
}
