package com.example.app.domain.product;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public class GetClassOfSortUseCase {
    private final ProductRepository repo;

    public GetClassOfSortUseCase(ProductRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String barcode, @NonNull Consumer<Status<Void>> callback) {
        repo.getClassOfSort(barcode, callback);
    }
}
