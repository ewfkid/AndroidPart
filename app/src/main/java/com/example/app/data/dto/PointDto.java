package com.example.app.data.dto;

import com.example.app.domain.entities.FullUserEntity;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

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
    @SerializedName("photoUrl")
    public String photoUrl;

    @Nullable
    @SerializedName("user")
    public FullUserEntity user;

}
