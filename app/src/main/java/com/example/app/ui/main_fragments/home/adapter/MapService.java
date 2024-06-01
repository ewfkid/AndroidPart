package com.example.app.ui.main_fragments.home.adapter;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MapService implements OnMapReadyCallback,  GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }
}
