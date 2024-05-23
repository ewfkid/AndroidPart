package com.example.app.ui.main_fragments.profile;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app.data.UserRepositoryImpl;
import com.example.app.domain.GetUserByIdUseCase;
import com.example.app.domain.entities.FullUserEntity;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<State> stateLiveData = mutableStateLiveData;

    public final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdUseCase(
            UserRepositoryImpl.getInstance()
    );

    public class State {
        @Nullable
        private final String errorMessage;

        @Nullable
        private final FullUserEntity user;


        public State(@Nullable String errorMessage, @Nullable FullUserEntity user) {
            this.errorMessage = errorMessage;
            this.user = user;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }

        @Nullable
        public FullUserEntity getUser() {
            return user;
        }
    }
}
