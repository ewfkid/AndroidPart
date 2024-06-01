package com.example.app.ui.main_fragments.scanner.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import com.example.app.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class QRAnalyzer implements ImageAnalysis.Analyzer {

    private final MultiFormatReader formatReader;
    private static Context context;
    public static Result result;
    public ImageProxy imageProxy;

    public QRAnalyzer() {
        this.formatReader = new MultiFormatReader();
        Map<DecodeHintType, Object> hintTypeObjectMap = new EnumMap<DecodeHintType, Object>(
                DecodeHintType.class
        );
        hintTypeObjectMap.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.of(BarcodeFormat.EAN_13));
        formatReader.setHints(hintTypeObjectMap);
    }

    @Override
    public void analyze(@NonNull ImageProxy image) {

    }

    public static void launchDialogError() {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.incorrect_barcode_fragment);
        TextView button = dialog.findViewById(R.id.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
