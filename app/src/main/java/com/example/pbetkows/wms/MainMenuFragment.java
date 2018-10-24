package com.example.pbetkows.wms;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pbetkows.wms.goodsReceipt.AddItemsToListFragment;
import com.example.pbetkows.wms.goodsReceipt.GoodsReceiptMainMenu;
import com.example.pbetkows.wms.tests.SampleList;
import com.example.pbetkows.wms.utils.DataWedgeFragment;
import com.example.pbetkows.wms.utils.MessageBox;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainMenuFragment extends Fragment {

//    @BindView(R.id.sample)
//    Button sampleButton;
//    @BindView(R.id.GoodsReceiptButton)
//    Button goodsReceiptButton;
//    @BindView(R.id.dataWedgeButton)
//    Button dataWedgeButton;

    @BindView(R.id.main_menu_list)
    ListView listView;

    String [] items = new String[] {"Goods Receipt", "Stock Transfer", "Data Wedge"};




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menu_fragment, container, false);
        ButterKnife.bind(this, view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            switch (items[position]) {
                case ("Goods Receipt"): navigate(new GoodsReceiptMainMenu());break;
                case ("Stock Transfer"): navigate(new SampleList());break;
                case ("Data Wedge"): navigate(new DataWedgeFragment());break;
            }
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
