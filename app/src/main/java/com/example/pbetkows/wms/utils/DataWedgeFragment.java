package com.example.pbetkows.wms.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pbetkows.wms.R;
import com.google.zxing.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class DataWedgeFragment extends Fragment {

    @BindView(R.id.scannerButton) Button scanButton;
    @BindView(R.id.resultEditText) EditText resultText;

    View view;

    private ZXingScannerView scannerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.data_wedge_fragment, container, false);

        ButterKnife.bind(this, view);

        scanButton.setOnClickListener(v -> {
            scannerView = new ZXingScannerView(getActivity());
            getActivity().setContentView(scannerView);
            scannerView.setResultHandler(result -> {
               MessageBox.Show(getContext(), result.getText());
               scannerView.stopCamera();

                if(view.getParent()!=null)
                    ((ViewGroup)view.getParent()).removeView(view); // <- fix
               getActivity().setContentView(view);
               resultText.setText(result.getText());
            });
            scannerView.startCamera();


        });
        return view;
    }

    private void navigate(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


}
