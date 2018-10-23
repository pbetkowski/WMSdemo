package com.example.pbetkows.wms.goodsReceipt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pbetkows.wms.MainActivity;
import com.example.pbetkows.wms.R;

import java.util.Objects;

public class GoodsReceiptMainMenu extends Fragment {


    Button createButton;
    Button createdButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.goods_receipt_menu, container, false);

        createButton = view.findViewById(R.id.createButton);
        createdButton = view.findViewById(R.id.createdButton);

        createButton.setOnClickListener(v -> navigate(new GoodsReceipt()));


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
