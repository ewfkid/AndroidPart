package com.example.app.domain.entities;

import androidx.annotation.NonNull;

public class PlaceEntity {

    @NonNull
    private final String address;

    private final double latitude;

    private final double longitude;

    public PlaceEntity(@NonNull String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
