package com.example.pbetkows.wms.goodsReceipt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pbetkows.wms.MainActivity;
import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.tests.SampleList;
import com.example.pbetkows.wms.utils.DataWedgeFragment;
import com.example.pbetkows.wms.utils.MessageBox;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.exceptions.OnErrorNotImplementedException;

public class GoodsReceiptMainMenu extends Fragment {


    @BindView(R.id.goodsReceiptMenu)
    ListView goodsReceiptMenu;
    String [] items = new String[] {"New Goods Receipt", "Goods Receipt Log"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.goods_receipt_menu, container, false);
        ButterKnife.bind(this, view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        goodsReceiptMenu.setAdapter(adapter);

        goodsReceiptMenu.setOnItemClickListener((parent, view1, position, id) -> {
            switch (items[position]) {
                case ("New Goods Receipt"):
                    navigate(new ChooseClientFragment())
                    ;break;
                case ("Stock Transfer"):
                    MessageBox.Show(getContext(), "Not implemented yet");
                    break;
            }
        });



        //createButton.setOnClickListener(v -> navigate(new ChooseClientFragment()));


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
