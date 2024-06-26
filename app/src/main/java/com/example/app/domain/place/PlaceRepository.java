package com.example.app.domain.place;

import androidx.annotation.NonNull;

import com.example.app.domain.entities.PlaceEntity;
import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public interface PlaceRepository {
    void getAddress(@NonNull String classOfSort, @NonNull Consumer<Status<PlaceEntity>> callback);
}
