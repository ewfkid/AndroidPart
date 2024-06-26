package com.example.app.domain.sign;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public class CreateUserUseCase {

    private final SignUserRepository repo;

    public CreateUserUseCase(SignUserRepository repo) {
        this.repo = repo;
    }

    public void execute(
            @NonNull String login,
            @NonNull String password,
            @NonNull Consumer<Status<Void>> callback
    ) {
        repo.createAccount(login, password, callback);
    }
}
