package com.example.app.data.dto;

import androidx.annotation.Nullable;
import com.example.app.domain.entities.UserEntity;
import com.google.gson.annotations.SerializedName;

public class PointDto {
    @Nullable
    @SerializedName("id")
    public String id;

    @Nullable
    @SerializedName("name")
    public String name;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @Nullable
    @SerializedName("address")
    public String address;

    @Nullable
    @SerializedName("user")
    public UserEntity user;

    public PointDto(@Nullable String name, double latitude, double longitude, @Nullable String address, @Nullable UserEntity user) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.user = user;
    }
}
