package com.example.app.ui.home.adapter;


import static android.graphics.Color.TRANSPARENT;
import static android.view.Gravity.BOTTOM;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.app.R;
import com.example.app.domain.entities.UserEntity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MapService implements OnMapReadyCallback {

    private final Context context;

    private final UserEntity user;






    public MapService(Context context, UserEntity user) {
        this.context = context;
        this.user = user;
    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {


    }

    private void openInputDialog(double latitude, double longitude, GoogleMap googleMap){
        BottomSheetDialog dialog = new BottomSheetDialog(context);

        dialog.setContentView(R.layout.item_point_dialog);
        dialog.getWindow().setGravity(BOTTOM);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        dialog.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
        dialog.show();

        TextView title = dialog.getWindow().findViewById(R.id.name);

    }


}
