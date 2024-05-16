package com.example.app.data;

import androidx.annotation.NonNull;

import com.example.app.domain.PointRepository;
import com.example.app.domain.entities.FullPointEntity;
import com.example.app.domain.entities.ItemPointEntity;
import com.example.app.domain.entities.Status;

import java.util.List;
import java.util.function.Consumer;

public class PointRepositoryImpl implements PointRepository {
    @Override
    public void getPoint(@NonNull String id, @NonNull Consumer<Status<FullPointEntity>> callback) {

    }

    @Override
    public void getAllPointsByUserId(@NonNull String userId, @NonNull Consumer<Status<List<ItemPointEntity>>> callback) {

    }
}
