package com.example.app.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.app.R;
import com.example.app.databinding.ProfileFragmentBinding;
import com.example.app.domain.entities.UserEntity;
import com.example.app.ui.edit_profile.EditProfileFragment;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private ProfileFragmentBinding binding;

    private ProfileViewModel viewModel;

    private static final String KEY_ID = "id";

    public ProfileFragment() {
        super(R.layout.profile_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = ProfileFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            final UserEntity entity = state.getUser();
            if (entity == null) return;
            binding.name.setText(entity.getName());
            binding.userEmail.setText(entity.getEmail());
            if (entity.getPhotoUrl() != null) {
                Picasso.get().load(entity.getPhotoUrl()).into(binding.userAvatar);
            } else {
                binding.userAvatar.setImageResource(R.drawable.user_avatar);
            }
            binding.logOut.setOnClickListener(v -> viewModel.logout());
            //TODO: solve something with this (SUFFER!!!!!)
            //binding.editProfile.setOnClickListener(v -> editProfile(id));

        });

        String id = getArguments() != null ? getArguments().getString(KEY_ID) : null;
        if (id == null) throw new IllegalStateException("ID is null");

        viewModel.load(id);

        subscribe(viewModel);
    }

    private void subscribe(ProfileViewModel viewModel) {

        viewModel.logoutLiveData.observe(getViewLifecycleOwner(), (unused -> {
            View view = getView();
            if (view == null) return;
            Navigation.findNavController(view).navigate(R.id.action_profile_fragment_to_sign_fragment);
        }));

        viewModel.editProfileLiveData.observe(getViewLifecycleOwner(), (unused -> {
            final View view = getView();
            if (view == null) return;
            Navigation.findNavController(view).navigate(R.id.action_profile_fragment_to_edit_profile_fragment);
        }));
    }

    private void editProfile(@NonNull String id) {
        View view = getView();
        if (view == null) return;
        Navigation.findNavController(view).navigate(
                R.id.action_profile_fragment_to_edit_profile_fragment,
                EditProfileFragment.getBundle(id)
        );
    }


    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
