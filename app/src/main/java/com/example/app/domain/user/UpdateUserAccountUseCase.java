package com.example.app.domain.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app.domain.entities.Status;
import com.example.app.domain.entities.UserEntity;

import java.util.function.Consumer;

public class UpdateUserAccountUseCase {
    private final UserRepository repo;

    public UpdateUserAccountUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute (@NonNull UserEntity user, @NonNull Consumer<Status<Void>> callback){
        repo.updateUser(user, callback);
    }
}
