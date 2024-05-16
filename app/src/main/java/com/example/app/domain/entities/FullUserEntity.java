package com.example.app.domain.entities;

import org.checkerframework.checker.nullness.qual.NonNull;

public class FullUserEntity {
    @NonNull
    private final String id;

    @NonNull
    private final String name;

    @NonNull
    private final String email;


    public FullUserEntity(@NonNull String id, @NonNull String name, @NonNull String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
}
