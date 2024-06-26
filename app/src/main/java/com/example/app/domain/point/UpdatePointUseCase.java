package com.example.app.domain.point;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.PointEntity;
import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public class UpdatePointUseCase {
    private final PointRepository repo;

    public UpdatePointUseCase(PointRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String id, @NonNull String name, @NonNull Consumer<Status<PointEntity>> callback){
        repo.updatePoint(id, name, callback);
    }
}
