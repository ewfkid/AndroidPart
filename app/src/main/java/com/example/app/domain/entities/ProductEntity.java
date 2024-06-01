package com.example.app.domain.entities;

public class ProductEntity {
    private final String barcode;

    private final String material;

    public ProductEntity(String barcode, String material) {
        this.barcode = barcode;
        this.material = material;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getMaterial() {
        return material;
    }
}
