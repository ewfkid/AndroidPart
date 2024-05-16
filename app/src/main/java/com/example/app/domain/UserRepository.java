package com.example.app.domain;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.FullUserEntity;
import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public interface UserRepository {
    void getUser(@NonNull String id, @NonNull Consumer<Status<FullUserEntity>> callback);
}
