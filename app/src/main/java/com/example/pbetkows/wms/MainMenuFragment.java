package com.example.pbetkows.wms;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pbetkows.wms.goodsReceipt.GoodsReceiptMainMenu;
import com.example.pbetkows.wms.utils.Alerts;
import com.example.pbetkows.wms.utils.DataWedgeFragment;
import com.example.pbetkows.wms.utils.MessageBox;
import com.example.pbetkows.wms.utils.Navigator;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainMenuFragment extends Fragment {

    @BindView(R.id.main_menu_list) ListView listView;

    //private String [] items = new String[] {"Goods Receipt", "Stock Transfer", "Data Wedge"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menu_fragment, container, false);
        ButterKnife.bind(this, view);

        setListView();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setListView() {

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, items);
//        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            switch (position) {
                case (0):
                    Navigator.navigate(getFragmentManager() ,new GoodsReceiptMainMenu());
                break;

                case (1):
                    Alerts.createAlert(getActivity(), () -> {
                        MessageBox.show(getContext(), "Bla");
                    });
                break;

                case (2):
                    Navigator.navigate(getFragmentManager() ,new DataWedgeFragment());
                break;
            }
        });
    }
}
