package com.example.app.domain.user;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.UserEntity;
import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public class GetUserByIdUseCase {
    private final UserRepository repo;

    public GetUserByIdUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<UserEntity>> callback) {
        repo.getUser(id, callback);
    }
}
