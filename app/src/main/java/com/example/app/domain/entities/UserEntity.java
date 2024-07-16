package com.example.app.domain.entities;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserEntity {
    @NonNull
    private final String id;

    @NonNull
    private final String email;

    @Nullable
    private String name;

    @Nullable
    private String photoUrl;


    public UserEntity(@NonNull String id, @NonNull String email, @Nullable String name, @Nullable String photoUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

}
