package com.example.app.domain.point;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.PointEntity;
import com.example.app.domain.entities.Status;
import com.example.app.domain.entities.UserEntity;

import java.util.function.Consumer;

public class CreatePointUseCase {
    private final PointRepository repo;

    public CreatePointUseCase(PointRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String name,
                        double latitude,
                        double longitude,
                        @NonNull String address,
                        @NonNull UserEntity user,
                        @NonNull Consumer<Status<PointEntity>> callback) {
        repo.createPoint(name, latitude, longitude, address, user, callback);
    }
}
