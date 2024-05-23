package com.example.app.data;

import androidx.annotation.NonNull;

import com.example.app.data.dto.PointDto;
import com.example.app.data.source.PointApi;
import com.example.app.data.utils.CallToConsumer;
import com.example.app.domain.PointRepository;
import com.example.app.domain.entities.FullPointEntity;
import com.example.app.domain.entities.FullUserEntity;
import com.example.app.domain.entities.ItemPointEntity;
import com.example.app.domain.entities.Status;
import com.example.app.data.network.RetrofitFactory;

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
    public void getPoint(@NonNull String id, @NonNull Consumer<Status<FullPointEntity>> callback) {
        pointApi.getById(id).enqueue(new CallToConsumer<>(
                callback,
                point -> {
                    final String resultId = point.id;
                    final String name = point.name;
                    final double latitude = point.latitude;
                    final double longitude = point.longitude;
                    final String address = point.address;
                    final String photoUrl = point.photoUrl;
                    final FullUserEntity user = point.user;

                    if (resultId != null && name != null && address != null && photoUrl != null && user != null) {
                        return new FullPointEntity(
                                resultId,
                                name,
                                latitude,
                                longitude,
                                address,
                                photoUrl,
                                user
                        );
                    } else {
                        return null;
                    }
                }
        ));
    }

    @Override
    public void getAllPointsByUserId(@NonNull String userId, @NonNull Consumer<Status<List<ItemPointEntity>>> callback) {
        pointApi.getByUserId(userId).enqueue(new CallToConsumer<>(
                callback,
                pointsDto -> {
                    ArrayList<ItemPointEntity> result = new ArrayList<>(pointsDto.size());
                    for (PointDto point : pointsDto) {
                        final String id = point.id;
                        final String name = point.name;
                        if (id != null && name != null) {
                            result.add(new ItemPointEntity(id, name));
                        }
                    }
                    return result;
                }
        ));
    }
}
