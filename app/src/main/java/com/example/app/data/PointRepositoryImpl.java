package com.example.app.data;

import androidx.annotation.NonNull;

import com.example.app.data.dto.PointDto;
import com.example.app.data.network.RetrofitFactory;
import com.example.app.data.source.PointApi;
import com.example.app.data.utils.CallToConsumer;
import com.example.app.domain.entities.PointEntity;
import com.example.app.domain.entities.Status;
import com.example.app.domain.entities.UserEntity;
import com.example.app.domain.point.PointRepository;

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
                                latitude,
                                longitude,
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
                                    latitude,
                                    longitude,
                                    address,
                                    user
                            ));
                        }
                    }
                    return result;
                }
        ));
    }

    @Override
    public void createPoint(@NonNull String name,
                            double latitude,
                            double longitude,
                            @NonNull String address,
                            @NonNull UserEntity user,
                            @NonNull Consumer<Status<PointEntity>> callback) {
        pointApi.createPoint(new PointDto(name, latitude, longitude, address, user)).enqueue(new CallToConsumer<>(
                callback,
                point -> {
                    final String resultId = point.id;
                    final String resultName = point.name;
                    final double resultLatitude = point.latitude;
                    final double resultLongitude = point.longitude;
                    final String resultAddress = point.address;
                    final UserEntity resultUser = point.user;

                    if (resultId != null && resultName != null && resultAddress != null && resultUser != null) {
                        return new PointEntity(
                                resultId,
                                resultName,
                                resultLatitude,
                                resultLongitude,
                                resultAddress,
                                resultUser
                        );
                    } else {
                        return null;
                    }
                }

        ));
    }

    @Override
    public void deletePoint(@NonNull String id, @NonNull Consumer<Status<Void>> callback) {
        pointApi.deleteById(id).enqueue(new CallToConsumer<>(
                callback,
                dto -> null));
    }

    @Override
    public void updatePoint(@NonNull String id, @NonNull String name, @NonNull Consumer<Status<PointEntity>> callback) {
        pointApi.update(id, name).enqueue(new CallToConsumer<>(
                callback,
                point -> {
                    final String resultId = point.id;
                    final String resultName = point.name;
                    final double latitude = point.latitude;
                    final double longitude = point.longitude;
                    final String address = point.address;
                    final UserEntity user = point.user;

                    if (resultId != null && resultName != null && address != null && user != null) {
                        return new PointEntity(
                                resultId,
                                resultName,
                                latitude,
                                longitude,
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
    public void getPointByName(@NonNull String name, @NonNull Consumer<Status<PointEntity>> callback) {
        pointApi.getByName(name).enqueue(new CallToConsumer<>(
                callback,
                point -> {
                    final String id = point.id;
                    final String resultName = point.name;
                    final double latitude = point.latitude;
                    final double longitude = point.longitude;
                    final String address = point.address;
                    final UserEntity user = point.user;

                    if (id != null && resultName != null && address != null && user != null) {
                        return new PointEntity(
                                id,
                                resultName,
                                latitude,
                                longitude,
                                address,
                                user
                        );
                    } else {
                        return null;
                    }
                }
        ));
    }
}
