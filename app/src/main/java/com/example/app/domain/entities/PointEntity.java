package com.example.app.domain.entities;

import androidx.annotation.NonNull;


public class PointEntity {

    @NonNull
    private final String id;

    @NonNull
    private final String name;

    private final double latitude;
    private final double longitude;

    @NonNull
    private final String address;

    @NonNull
    private final UserEntity user;


    public PointEntity(@NonNull String id,
                       @NonNull String name,
                       double latitude,
                       double longitude,
                       @NonNull String address,
                       @NonNull UserEntity user) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }

    public UserEntity getUser() {
        return user;
    }
}
