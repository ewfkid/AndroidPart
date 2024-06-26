package com.example.app.ui.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app.data.UserRepositoryImpl;
import com.example.app.domain.entities.UserEntity;
import com.example.app.domain.sign.LogoutUseCase;
import com.example.app.domain.user.GetUserByIdUseCase;

public class ProfileViewModel extends ViewModel {

    /* LiveData */

    private final MutableLiveData<State> mutableStateLiveData = new MutableLiveData<>();
    public final LiveData<State> stateLiveData = mutableStateLiveData;

    private final MutableLiveData<Void> mutableLogoutLiveData = new MutableLiveData<>();
    public final LiveData<Void> logoutLiveData = mutableLogoutLiveData;

    private final MutableLiveData<Void> mutableEditProfileLiveData = new MutableLiveData<>();
    public final LiveData<Void> editProfileLiveData = mutableEditProfileLiveData;

    /* LiveData */

    /* UseCases */

    public final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdUseCase(
            UserRepositoryImpl.getInstance()
    );


    public final LogoutUseCase logoutUseCase = new LogoutUseCase(
            UserRepositoryImpl.getInstance()
    );

    /* UseCases */

    public void load(@NonNull String id) {
        mutableStateLiveData.setValue(new State(null, null));
        getUserByIdUseCase.execute(id, (status) -> {
            mutableStateLiveData.postValue(new State(
                    status.getErrors() != null ? status.getErrors().getLocalizedMessage() : null,
                    status.getValue()
            ));
        });
    }

    public void logout() {
        logoutUseCase.execute();
        mutableLogoutLiveData.postValue(null);
    }


    public class State {

        @Nullable
        private final String errorMessage;

        @Nullable
        private final UserEntity user;


        public State(@Nullable String errorMessage, @Nullable UserEntity user) {
            this.errorMessage = errorMessage;
            this.user = user;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public UserEntity getUser() {
            return user;
        }

    }
}
