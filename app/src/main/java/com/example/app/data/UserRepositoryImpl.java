package com.example.app.data;

import androidx.annotation.NonNull;

import com.example.app.domain.UserRepository;
import com.example.app.domain.entities.FullUserEntity;
import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void getUser(@NonNull String id, @NonNull Consumer<Status<FullUserEntity>> callback) {

    }
}
