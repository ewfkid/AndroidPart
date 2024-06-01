package com.example.app.data.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AccountDto {
    @NonNull
    @SerializedName("password")
    public String password;

    @NonNull
    @SerializedName("email")
    public String email;

    public AccountDto(@NonNull String password, @NonNull String email) {
        this.password = password;
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }
}
