package com.example.app.domain.place;

import androidx.annotation.NonNull;

import com.example.app.data.PlaceRepositoryImpl;
import com.example.app.domain.entities.PlaceEntity;
import com.example.app.domain.entities.Status;

import java.util.function.Consumer;

public class GetAddressUseCase {
    private final PlaceRepositoryImpl repo;

    public GetAddressUseCase(PlaceRepositoryImpl repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String classOfSort, @NonNull Consumer<Status<PlaceEntity>> callback){
        repo.getAddress(classOfSort, callback);
    }
}
