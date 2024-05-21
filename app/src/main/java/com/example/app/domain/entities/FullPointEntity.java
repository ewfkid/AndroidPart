package com.example.app.domain.entities;

import org.checkerframework.checker.nullness.qual.NonNull;

public class FullPointEntity {

    @NonNull
    private final String id;

    @NonNull
    private final String name;

    private double latitude;

    private double longitude;

    @NonNull
    private final String address;

    @NonNull
    private final String photoUrl;

    @NonNull
    private final FullUserEntity user;

    public FullPointEntity(@NonNull String id,
                           @NonNull String name,
                           double latitude,
                           double longitude,
                           @NonNull String address,
                           @NonNull String photoUrl,
                           @NonNull FullUserEntity user) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.photoUrl = photoUrl;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public FullUserEntity getUser() {
        return user;
    }
}
