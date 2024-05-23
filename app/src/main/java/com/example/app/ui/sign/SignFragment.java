package com.example.app.ui.sign;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;
import com.example.app.databinding.SignFragmentBinding;

public class SignFragment extends Fragment {
    private SignFragmentBinding binding;

    private SignViewModel viewModel;

    public SignFragment() {
        super(R.layout.scanner_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = SignFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(SignViewModel.class);
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
