package com.example.app.data;

import androidx.annotation.NonNull;

import com.example.app.data.source.UserApi;
import com.example.app.data.utils.CallToConsumer;
import com.example.app.domain.UserRepository;
import com.example.app.domain.entities.FullUserEntity;
import com.example.app.domain.entities.Status;
import com.example.app.network.RetrofitFactory;

import java.util.function.Consumer;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepositoryImpl INSTANCE;

    private UserApi userApi = RetrofitFactory.getInstance().getUserApi();

    private UserRepositoryImpl() {
    }

    public static synchronized UserRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepositoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public void getUser(@NonNull String id, @NonNull Consumer<Status<FullUserEntity>> callback) {
        userApi.getById(id).enqueue(new CallToConsumer<>(
                callback,
                user -> {
                    final String resultId = user.id;
                    final String email = user.email;
                    final String name = user.name;
                    if (resultId != null && email != null && name != null) {
                        return new FullUserEntity(
                                resultId,
                                email,
                                name
                        );
                    } else {
                        return null;
                    }
                }
        ));
    }
}
