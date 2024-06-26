package com.example.app.domain.point;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.PointEntity;
import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public class GetPointByNameUseCase {
    private final PointRepository repo;

    public GetPointByNameUseCase(PointRepository repo) {
        this.repo = repo;
    }

    public void execute (@NonNull String name, @NonNull Consumer<Status<PointEntity>> callback){
        repo.getPointByName(name, callback);
    }
}
