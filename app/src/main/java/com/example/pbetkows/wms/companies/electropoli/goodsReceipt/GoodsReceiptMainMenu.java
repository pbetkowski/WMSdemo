package com.example.pbetkows.wms.companies.electropoli.goodsReceipt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.utils.Navigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsReceiptMainMenu extends Fragment {


    @BindView(R.id.goodsReceiptMenu) ListView goodsReceiptMenu;

    private List<String> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.goods_receipt_menu, container, false);
        ButterKnife.bind(this, view);

        goodsReceiptMenu.setOnItemClickListener((parent, view1, position, id) -> {
            switch (position) {
                case (0):
                    Navigator.navigate(getFragmentManager(), new ChooseClientFragment());
                    break;
                case (1):
                    Navigator.navigate(getFragmentManager(), new GoodsReceiptLogFragment());
                    break;
            }
        });


        return view;
    }
}
