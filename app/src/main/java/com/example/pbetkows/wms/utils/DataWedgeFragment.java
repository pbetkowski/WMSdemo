package com.example.pbetkows.wms.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pbetkows.wms.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class DataWedgeFragment extends Fragment {

    Button scanButton;
    EditText resultText;
    String barcode;
    private ZXingScannerView scannerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.data_wedge_fragment, container, false);

        resultText = view.findViewById(R.id.resultEditText);
        scanButton = view.findViewById(R.id.scannerButton);

        scanButton.setOnClickListener(v -> {
            scannerView = new ZXingScannerView(getActivity());
            getActivity().setContentView(scannerView);
            scannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
                @Override
                public void handleResult(Result result) {
                    Toast.makeText(getActivity(), "Contents = " + result.getText() + ", Format = " + result.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            scannerView.startCamera();
        });
        return view;
    }
}
