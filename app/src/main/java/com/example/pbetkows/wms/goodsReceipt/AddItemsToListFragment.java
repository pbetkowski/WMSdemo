package com.example.pbetkows.wms.goodsReceipt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.utils.MessageBox;
import com.example.pbetkows.wms.utils.StaticGenerators;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class AddItemsToListFragment extends Fragment {

    @BindView(R.id.clientTextView) TextView clientTextView;
    @BindView(R.id.documentNuberTextView) TextView documentNumberTextView;
    @BindView(R.id.docDateTextView) TextView docDateTextView;
    @BindView(R.id.itemList) ListView itemList;
    @BindView(R.id.addItemToGoodsReceipt) FloatingActionButton addItemButton;

    View view;
    private ZXingScannerView scannerView;

    List<String> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_items_good_receipt_fragment, container, false);

        ButterKnife.bind(this, view);
        clientTextView.setText(getArguments().getString("key"));
        docDateTextView.setText(StaticGenerators.getCurrentDate());

        addItemButton.setOnClickListener(v -> {
            scannerView = new ZXingScannerView(getActivity());
            getActivity().setContentView(scannerView);
            scannerView.setResultHandler(result -> {
                MessageBox.Show(getContext(), result.getText());
                addToList(result.getText());
                scannerView.stopCamera();

                if(view.getParent()!=null)
                    ((ViewGroup)view.getParent()).removeView(view); // <- fix
                getActivity().setContentView(view);
            });
            scannerView.startCamera();
        });

        return view;
    }

    private void addToList(String barcode) {
        items.add(barcode);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        itemList.setAdapter(adapter);
    }



}
