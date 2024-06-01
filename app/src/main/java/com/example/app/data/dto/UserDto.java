package com.example.app.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class UserDto {

    @Nullable
    @SerializedName("id")
    public String id;

    @Nullable
    @SerializedName("email")
    public String email;

    @Nullable
    @SerializedName("name")
    public String name;

    @Nullable
    @SerializedName("photoUrl")
    public String photoUrl;
}
