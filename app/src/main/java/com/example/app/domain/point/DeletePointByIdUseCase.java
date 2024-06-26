package com.example.app.domain.point;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public class DeletePointByIdUseCase {
    private final PointRepository repo;

    public DeletePointByIdUseCase(PointRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<Void>> callback) {
       repo.deletePoint(id, callback);
    }
}
