package com.example.app.domain.point;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.PointEntity;
import com.example.app.domain.entities.Status;

import java.util.List;
import java.util.function.Consumer;

public interface PointRepository {
    void getPoint(@NonNull String id, @NonNull Consumer<Status<PointEntity>> callback);

    void getAllPointsByUserId(@NonNull String userId, @NonNull Consumer<Status<List<PointEntity>>> callback);
}
