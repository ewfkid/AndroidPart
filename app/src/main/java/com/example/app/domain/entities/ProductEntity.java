package com.example.app.domain.entities;

import androidx.annotation.NonNull;

public class ProductEntity {
    @NonNull
    private final String barcode;

    @NonNull
    private final String material;

    public ProductEntity(@NonNull String barcode, @NonNull String material) {
        this.barcode = barcode;
        this.material = material;
    }

    @NonNull
    public String getBarcode() {
        return barcode;
    }

    @NonNull
    public String getMaterial() {
        return material;
    }
}
