package com.example.app.ui.main_fragments.scanner;


import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.app.R;
import com.example.app.databinding.ScannerFragmentBinding;
import com.example.app.ui.main_fragments.scanner.adapter.QRAnalyzer;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class ScannerFragment extends Fragment {
    private ScannerFragmentBinding binding;

    private ScannerViewModel viewModel;

    private static final int PERMISSION_REQUEST_CODE = 10;
    private QRAnalyzer qrAnalyzer;

    public ScannerFragment() {
        super(R.layout.scanner_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = ScannerFragmentBinding.bind(view);

        if (allPermissionGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(
                    ScannerFragment.this.getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CODE
            );
        }

    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    private boolean allPermissionGranted() {
        return ContextCompat.checkSelfPermission(
                ScannerFragment.this.getContext(),
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> providerListenableFuture = ProcessCameraProvider
                .getInstance(this.requireContext());

        providerListenableFuture.addListener(() -> {
                    try {
                        ProcessCameraProvider cameraProvider = providerListenableFuture.get();

                        Preview preview = new Preview.Builder().build();
                        preview.setSurfaceProvider(binding.pvCamera.getSurfaceProvider());

                        CameraSelector cameraSelector = new CameraSelector.Builder()
                                .requireLensFacing(CameraSelector.LENS_FACING_BACK).build();

                        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build();
                        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this.getContext()),
                                qrAnalyzer);

                        Camera camera = cameraProvider.bindToLifecycle(
                                this,
                                cameraSelector,
                                preview,
                                imageAnalysis
                        );
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                ContextCompat.getMainExecutor(this.requireContext())
        );

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (allPermissionGranted()) {
                startCamera();
            }
        }
    }
}
