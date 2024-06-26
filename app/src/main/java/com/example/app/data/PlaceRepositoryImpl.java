package com.example.app.data;

import androidx.annotation.NonNull;

import com.example.app.data.network.PlaceController;
import com.example.app.data.source.PlaceApi;
import com.example.app.data.utils.CallToConsumer;
import com.example.app.domain.entities.PlaceEntity;
import com.example.app.domain.entities.Status;
import com.example.app.domain.place.PlaceRepository;

import java.util.function.Consumer;

public class PlaceRepositoryImpl implements PlaceRepository {

    private static PlaceRepositoryImpl INSTANCE;

    private PlaceApi placeApi = PlaceController.getInstance().getPlaceApi();

    private static final String API_KEY = "api_key_example";

    private PlaceRepositoryImpl() {
    }

    public static synchronized PlaceRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlaceRepositoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public void getAddress(@NonNull String classOfSort, @NonNull Consumer<Status<PlaceEntity>> callback) {
        placeApi.getPlaceDetails(classOfSort, API_KEY).enqueue(new CallToConsumer<>(callback,
                place -> {
                    return new PlaceEntity(place.getAddress(), place.getLatitude(), place.getLongitude());
                }
        ));
    }
}
