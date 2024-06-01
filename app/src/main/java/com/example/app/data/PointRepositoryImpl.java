package com.example.app.data;

import androidx.annotation.NonNull;

import com.example.app.data.dto.PointDto;
import com.example.app.data.source.PointApi;
import com.example.app.data.utils.CallToConsumer;
import com.example.app.domain.point.PointRepository;
import com.example.app.domain.entities.PointEntity;
import com.example.app.domain.entities.UserEntity;
import com.example.app.domain.entities.Status;
import com.example.app.data.network.RetrofitFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PointRepositoryImpl implements PointRepository {

    private static PointRepositoryImpl INSTANCE;

    private PointApi pointApi = RetrofitFactory.getInstance().getPointApi();

    private PointRepositoryImpl() {
    }

    public static synchronized PointRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PointRepositoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public void getPoint(@NonNull String id, @NonNull Consumer<Status<PointEntity>> callback) {
        pointApi.getById(id).enqueue(new CallToConsumer<>(
                callback,
                point -> {
                    final String resultId = point.id;
                    final String name = point.name;
                    final double latitude = point.latitude;
                    final double longitude = point.longitude;
                    final String address = point.address;
                    final UserEntity user = point.user;

                    if (resultId != null && name != null && address != null && user != null) {
                        return new PointEntity(
                                resultId,
                                name,
                                new LatLng(latitude, longitude),
                                address,
                                user
                        );
                    } else {
                        return null;
                    }
                }
        ));
    }

    @Override
    public void getAllPointsByUserId(@NonNull String userId, @NonNull Consumer<Status<List<PointEntity>>> callback) {
        pointApi.getByUserId(userId).enqueue(new CallToConsumer<>(
                callback,
                pointsDto -> {
                    ArrayList<PointEntity> result = new ArrayList<>(pointsDto.size());
                    for (PointDto point : pointsDto) {
                        final String id = point.id;
                        final String name = point.name;
                        final double latitude = point.latitude;
                        final double longitude = point.longitude;
                        final String address = point.address;
                        final UserEntity user = point.user;
                        if (id != null && name != null && address != null && user != null) {
                            result.add(new PointEntity(
                                    id,
                                    name,
                                    new LatLng(latitude, longitude),
                                    address,
                                    user
                            ));
                        }
                    }
                    return result;
                }
        ));
    }
}
