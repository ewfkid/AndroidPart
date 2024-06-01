package com.example.app.domain.product;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.Status;
import com.example.app.domain.entities.UserEntity;

import java.util.function.Consumer;

public interface ProductRepository {
    void getClassOfSort(@NonNull String barcode, @NonNull Consumer<Status<Void>> callback);
}
