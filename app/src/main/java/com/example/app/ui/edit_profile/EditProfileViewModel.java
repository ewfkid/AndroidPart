package com.example.app.ui.edit_profile;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app.data.UserRepositoryImpl;
import com.example.app.domain.entities.UserEntity;
import com.example.app.domain.user.UpdateUserProfileUseCase;

public class EditProfileViewModel extends ViewModel {

    /* LiveData */

    private final MutableLiveData<EditProfileViewModel.State> mutableStateLiveData = new MutableLiveData<>();
    public final LiveData<EditProfileViewModel.State> stateLiveData = mutableStateLiveData;

    private final MutableLiveData<Void> mutableProfileLiveData = new MutableLiveData<>();
    public final LiveData<Void> profileLiveData = mutableProfileLiveData;

    private final MutableLiveData<String> mutableErrorLiveData = new MutableLiveData<>();
    public final LiveData<String> errorLiveData = mutableErrorLiveData;

    /* LiveData */

    /* UseCases */

    public final UpdateUserProfileUseCase updateUserProfileUseCase = new UpdateUserProfileUseCase(
            UserRepositoryImpl.getInstance()
    );

    /* UseCases */

    @Nullable
    String email;

    @Nullable
    String name;

    @Nullable
    String photoUrl;


    public void changeEmail(@NonNull String email) {
        this.email = email;
    }

    public void changeName(@NonNull String name) {
        this.name = name;
    }

    public void changePhoto(@NonNull String photoUrl) {
        //TODO: implement this method
    }



    public void updateProfile(@NonNull String id) {
        if (email == null || email.isEmpty()){
            mutableStateLiveData.postValue(new State("Email cannot be null", null));
        }
        updateUserProfileUseCase.execute(
                id, new UserEntity(id, email, name, photoUrl), ustatus -> {
                    if (ustatus.getStatusCode() == 200 && ustatus.getErrors() == null) {
                        mutableProfileLiveData.postValue(null);
                    } else {
                        mutableErrorLiveData.postValue("Something wrong");
                    }
                } );
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
