package com.example.app.domain;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.FullPointEntity;
import com.example.app.domain.entities.ItemPointEntity;
import com.example.app.domain.entities.Status;

import java.util.List;
import java.util.function.Consumer;

public interface PointRepository {
    void getPoint(@NonNull String id, @NonNull Consumer<Status<FullPointEntity>> callback);

    void getAllPointsByUserId(@NonNull String userId, @NonNull Consumer<Status<List<ItemPointEntity>>> callback);
}
