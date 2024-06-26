package com.example.app.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app.data.PointRepositoryImpl;
import com.example.app.domain.entities.PointEntity;
import com.example.app.domain.entities.UserEntity;
import com.example.app.domain.point.DeletePointByIdUseCase;
import com.example.app.domain.point.GetPointByIdUseCase;
import com.example.app.domain.point.GetPointListByUserIdUseCase;
import com.example.app.domain.point.UpdatePointUseCase;
import com.example.app.ui.profile.ProfileViewModel;

import java.util.List;

public class HomeViewModel extends ViewModel {

    /* LiveData */
    private final MutableLiveData<ProfileViewModel.State> mutableStateLiveData = new MutableLiveData<>();
    public final LiveData<ProfileViewModel.State> stateLiveData = mutableStateLiveData;
    /* LiveData */


    /* UseCases */
    private final GetPointListByUserIdUseCase getPointListByUserIdUseCase = new GetPointListByUserIdUseCase(
            PointRepositoryImpl.getInstance()
    );

    private final UpdatePointUseCase updatePointUseCase = new UpdatePointUseCase(
            PointRepositoryImpl.getInstance()
    );

    private final DeletePointByIdUseCase deletePointByIdUseCase = new DeletePointByIdUseCase(
            PointRepositoryImpl.getInstance()
    );

    private final GetPointByIdUseCase getPointByIdUseCase = new GetPointByIdUseCase(
            PointRepositoryImpl.getInstance()
    );

    /* UseCases */



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
