package com.example.app.domain.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app.domain.entities.UserEntity;
import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public interface UserRepository {
    void getUser(@NonNull String id, @NonNull Consumer<Status<UserEntity>> callback);

    void updateUser(@NonNull UserEntity user, @NonNull Consumer<Status<Void>> callback);
}
