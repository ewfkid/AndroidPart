package com.example.app.ui.edit_profile;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.app.R;
import com.example.app.databinding.EditProfileFragmentBinding;
import com.example.app.ui.utils.OnChangeText;

public class EditProfileFragment extends Fragment {

    private EditProfileViewModel viewModel;

    private EditProfileFragmentBinding binding;


    private static final String KEY_ID = "id";


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = EditProfileFragmentBinding.bind(view);


        viewModel = new ViewModelProvider(this).get(EditProfileViewModel.class);
        binding.name.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                viewModel.changeName(s.toString());
            }
        });
        binding.email.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                viewModel.changeEmail(s.toString());
            }
        });
        binding.userAvatar.setImageResource(R.drawable.user_avatar);


        String id = getArguments() != null ? getArguments().getString(KEY_ID) : null;
        if (id == null) throw new IllegalStateException("ID is null");
        binding.save.setOnClickListener(v -> viewModel.updateProfile(id));
        subscribe(viewModel);
    }

    private void subscribe(EditProfileViewModel viewModel) {
        viewModel.errorLiveData.observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

        });

        viewModel.profileLiveData.observe(getViewLifecycleOwner(), (unused) -> {
            final View view = getView();
            if (view == null) return;
            Navigation.findNavController(view).navigate(R.id.action_edit_profile_fragment_to_profile_fragment);
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    public static Bundle getBundle(@NonNull String id) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID, id);
        return bundle;
    }


}
