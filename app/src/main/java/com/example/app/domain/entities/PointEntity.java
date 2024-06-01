package com.example.app.domain.entities;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;



public class PointEntity {

    @NonNull
    private final String id;

    @NonNull
    private final String name;

    @NonNull
    private final LatLng latLng;

    @NonNull
    private final String address;

    @NonNull
    private final UserEntity user;


    public PointEntity(@NonNull String id,
                       @NonNull String name,
                       @NonNull LatLng latLng,
                       @NonNull String address,
                       @NonNull UserEntity user) {
        this.id = id;
        this.name = name;
        this.latLng = latLng;
        this.address = address;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getAddress() {
        return address;
    }

    public UserEntity getUser() {
        return user;
    }
}
