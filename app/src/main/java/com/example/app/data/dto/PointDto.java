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
}
