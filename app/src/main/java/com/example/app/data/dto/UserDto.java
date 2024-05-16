package com.example.app.data.dto;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

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
}
