package com.example.app.domain.point;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.PointEntity;
import com.example.app.domain.entities.Status;

import java.util.List;
import java.util.function.Consumer;

public class GetPointListByUserIdUseCase {

    private final PointRepository repo;

    public GetPointListByUserIdUseCase(PointRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String userId, @NonNull Consumer<Status<List<PointEntity>>> callback) {
        repo.getAllPointsByUserId(userId, callback);
    }
}
