package com.example.pbetkows.wms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.util.Objects;

public class MainMenuFragment extends Fragment {

    Button sampleButton;

    Button goodsReceiptButton;

    Button dataWedgeButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menu_fragment, container, false);
        sampleButton = view.findViewById(R.id.sample);
        goodsReceiptButton = view.findViewById(R.id.GoodsReceiptButton);
        dataWedgeButton = view.findViewById(R.id.dataWedgeButton);

        sampleButton.setOnClickListener(v -> {
            ((MainActivity)Objects.requireNonNull(getActivity())).setViewPager(3);
        });

        goodsReceiptButton.setOnClickListener(v -> {
            ((MainActivity)Objects.requireNonNull(getActivity())).setViewPager(2);
        });

        dataWedgeButton.setOnClickListener(v -> {
            ((MainActivity)Objects.requireNonNull(getActivity())).setViewPager(5);
        });


        return view;
    }
}
