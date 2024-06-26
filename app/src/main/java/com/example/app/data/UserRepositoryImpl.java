package com.example.app.data;

import androidx.annotation.NonNull;

import com.example.app.data.dto.AccountDto;
import com.example.app.data.dto.UserDto;
import com.example.app.data.network.RetrofitFactory;
import com.example.app.data.source.CredentialsDataSource;
import com.example.app.data.source.UserApi;
import com.example.app.data.utils.CallToConsumer;
import com.example.app.domain.entities.Status;
import com.example.app.domain.entities.UserEntity;
import com.example.app.domain.sign.SignUserRepository;
import com.example.app.domain.user.UserRepository;

import java.util.function.Consumer;

public class UserRepositoryImpl implements UserRepository, SignUserRepository {
    private static UserRepositoryImpl INSTANCE;

    private UserApi userApi = RetrofitFactory.getInstance().getUserApi();

    private final CredentialsDataSource credentialsDataSource = CredentialsDataSource.getInstance();


    private UserRepositoryImpl() {
    }

    public static synchronized UserRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepositoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public void getUser(@NonNull String id, @NonNull Consumer<Status<UserEntity>> callback) {
        userApi.getById(id).enqueue(new CallToConsumer<>(
                callback,
                user -> {
                    final String resultId = user.id;
                    final String email = user.email;
                    if (resultId != null && email != null) {
                        return new UserEntity(
                                resultId,
                                email,
                                user.name,
                                user.photoUrl
                        );
                    } else {
                        return null;
                    }
                }
        ));

    }

    @Override
    public void updateUser(@NonNull String id, @NonNull UserEntity updatedUser, @NonNull Consumer<Status<UserEntity>> callback) {
        userApi.update(id, new UserDto(updatedUser.getEmail(), updatedUser.getName(), updatedUser.getPhotoUrl())).enqueue(new CallToConsumer<>(
                callback,
                user -> {
                    final String resultId = user.id;
                    final String email = user.email;
                    if (resultId != null && email != null) {
                        return new UserEntity(
                                resultId,
                                email,
                                user.name,
                                user.photoUrl
                        );
                    } else {
                        return null;
                    }
                }
        ));
    }

    @Override
    public void isExistUser(@NonNull String login, Consumer<Status<Void>> callback) {
        userApi.isExist(login).enqueue(new CallToConsumer<>(
                callback,
                dto -> null
        ));
    }

    @Override
    public void createAccount(@NonNull String login, @NonNull String password, Consumer<Status<Void>> callback) {
        userApi.register(new AccountDto(login, password)).enqueue(new CallToConsumer<>(
                callback,
                dto -> null
        ));
    }

    @Override
    public void login(@NonNull String login, @NonNull String password, Consumer<Status<Void>> callback) {
        credentialsDataSource.updateLogin(login, password);
        userApi = RetrofitFactory.getInstance().getUserApi();
        userApi.login().enqueue(new CallToConsumer<>(
                callback,
                dto -> null
        ));
    }

    @Override
    public void logout() {
        credentialsDataSource.logout();
    }
}
