package com.example.app.domain;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.FullPointEntity;
import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public class GetPointByIdUseCase {
    private final PointRepository repo;

    public GetPointByIdUseCase(PointRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<FullPointEntity>> callback) {
        repo.getPoint(id, callback);
    }
}
