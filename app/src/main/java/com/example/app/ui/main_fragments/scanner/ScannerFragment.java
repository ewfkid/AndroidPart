package com.example.app.ui.main_fragments.scanner;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;
import com.example.app.databinding.ScannerFragmentBinding;

public class ScannerFragment extends Fragment {
    private ScannerFragmentBinding binding;

    private ScannerViewModel viewModel;

    public ScannerFragment() {
        super(R.layout.scanner_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = ScannerFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(ScannerViewModel.class);
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
