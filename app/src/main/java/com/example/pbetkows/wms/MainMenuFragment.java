package com.example.pbetkows.wms;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pbetkows.wms.tests.SampleList;


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
            getFragmentManager().beginTransaction().replace(R.id.main_menu_panel, new SampleList()).commit();
        });


        return view;
    }
}
