package com.example.app.domain.point;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.PointEntity;
import com.example.app.domain.entities.Status;
import com.example.app.domain.entities.UserEntity;

import java.util.List;
import java.util.function.Consumer;

public interface PointRepository {
    void getPoint(@NonNull String id, @NonNull Consumer<Status<PointEntity>> callback);

    void getAllPointsByUserId(@NonNull String userId, @NonNull Consumer<Status<List<PointEntity>>> callback);

    void createPoint(@NonNull String name,
                        double latitude,
                        double longitude,
                        @NonNull String address,
                        @NonNull UserEntity user,
                        @NonNull Consumer<Status<PointEntity>> callback);

    void deletePoint(@NonNull String id,  @NonNull Consumer<Status<Void>> callback);

    void updatePoint(@NonNull String id, @NonNull String name, @NonNull Consumer<Status<PointEntity>> callback);

    void getPointByName(@NonNull String name, @NonNull Consumer<Status<PointEntity>> callback);
}
