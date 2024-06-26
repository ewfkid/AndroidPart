package com.example.app.domain.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app.domain.entities.Status;
import com.example.app.domain.entities.UserEntity;

import java.util.function.Consumer;

public class UpdateUserProfileUseCase {
    private final UserRepository repo;

    public UpdateUserProfileUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute (@NonNull String id, @NonNull UserEntity user, @NonNull Consumer<Status<UserEntity>> callback){
        repo.updateUser(id, user, callback);
    }
}
