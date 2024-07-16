package com.example.app.ui.profile;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.app.R;
import com.example.app.data.UserRepositoryImpl;
import com.example.app.databinding.ProfileFragmentBinding;
import com.example.app.domain.entities.UserEntity;
import com.example.app.domain.sign.LogoutUseCase;
import com.example.app.domain.user.UpdateUserProfileUseCase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

public class ProfileFragment extends Fragment {

    private ProfileFragmentBinding binding;

    private final UserEntity user;

    private String photoUrl;

    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance("gs://no-waste-app-b895f.appspot.com");

    private static final int PICK_IMAGE_REQUEST = 1;

    /* UseCase */

    private final UpdateUserProfileUseCase updateUserProfileUseCase = new UpdateUserProfileUseCase(
            UserRepositoryImpl.getInstance()
    );

    private final LogoutUseCase logoutUseCase = new LogoutUseCase(
            UserRepositoryImpl.getInstance()
    );

    /* UseCases */

    public ProfileFragment(UserEntity user) {
        super(R.layout.profile_fragment);
        this.user = user;
        this.photoUrl = user.getPhotoUrl();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        binding = ProfileFragmentBinding.bind(view);


        TextInputLayout editTextName = binding.textInputLayoutName;

        TextInputLayout editTextEmail = binding.textInputLayoutEmail;

        if (user.getName() != null && !user.getName().isEmpty()) {
            editTextName.getEditText().setText(user.getName());
        }

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            editTextEmail.getEditText().setText(user.getEmail());
        }


        binding.materialToolbar.setNavigationOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_profile_fragment_to_home_fragment)
        );

        binding.materialToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.edit) {
                item.setEnabled(false);

                FloatingActionButton button = new FloatingActionButton(getContext());
                CoordinatorLayout layout = binding.profileFragment;
                CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setAnchorId(R.id.text_input_layout_email);
                button.setTranslationY(200);
                button.setImageResource(R.drawable.ic_done);
                params.anchorGravity = GravityCompat.END | Gravity.BOTTOM;
                layout.addView(button, params);

                editTextName.setBoxStrokeWidth(5);
                editTextEmail.setBoxStrokeWidth(5);

                editTextName.setEnabled(true);
                editTextEmail.setEnabled(true);

                editTextName.requestFocus();

                button.setOnClickListener(v -> {
                    String updatedName = editTextName.getEditText().getText().toString();
                    String updatedEmail = editTextEmail.getEditText().getText().toString();

                    if (updatedName == null || updatedName.isEmpty() || updatedEmail == null || updatedEmail.isEmpty()) {
                        Toast.makeText(getActivity(), "Please enter your name and email", Toast.LENGTH_SHORT).show();
                    } else {
                        updateUserProfileUseCase.execute(
                                user.getId(),
                                new UserEntity(
                                        user.getId(),
                                        updatedEmail,
                                        updatedName,
                                        user.getPhotoUrl()),
                                userEntityStatus -> {

                                });
                        editTextName.setEnabled(false);
                        editTextEmail.setEnabled(false);

                        editTextName.setBoxStrokeWidth(0);
                        editTextEmail.setBoxStrokeWidth(0);

                        item.setEnabled(true);
                        layout.removeView(button);
                    }
                });

            } else if (item.getItemId() == R.id.logout) {
                logoutUseCase.execute();
                Navigation.findNavController(view).navigate(R.id.action_profile_fragment_to_sign_fragment);
            }

            return false;
        });

        if (user.getPhotoUrl() != null && user.getPhotoUrl().isEmpty()) {
            StorageReference reference = firebaseStorage.getReference(photoUrl);
            Glide.with(requireContext())
                    .load(reference)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(binding.profilePicture);
        }

        binding.addPhotoButton.setOnClickListener(v -> {
            showFileChooser();
        });


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri imageUri = data.getData();
            String[] projection = {MediaStore.Images.Media.WIDTH, MediaStore.Images.Media.HEIGHT};
            Cursor cursor = requireActivity().getContentResolver().query(imageUri, projection, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int widthIndex = cursor.getColumnIndex(MediaStore.Images.Media.WIDTH);
                int heightIndex = cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT);
                int width = cursor.getInt(widthIndex);
                int height = cursor.getInt(heightIndex);

                if (width <= height) {
                    Toast.makeText(getActivity(), "Orientation must be horizontal", Toast.LENGTH_SHORT).show();
                    return;
                }

                cursor.close();
            }


            if (photoUrl == null || photoUrl.isEmpty()) {
                photoUrl = "/background" + user.getId();
                updateUserProfileUseCase.execute(user.getId(), new UserEntity(
                                user.getId(),
                                user.getEmail(),
                                user.getName(),
                                photoUrl),
                        userEntityStatus -> {

                        });
            }

            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setCacheControl("no-cache, no-store, must-revalidate")
                    .build();

            StorageReference reference = firebaseStorage.getReference().child(photoUrl);
            reference.putFile(imageUri, metadata);

            Glide.with(requireContext()).load(imageUri).into(binding.profilePicture);
        }
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
