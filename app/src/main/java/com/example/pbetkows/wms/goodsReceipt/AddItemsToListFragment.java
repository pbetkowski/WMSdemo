package com.example.pbetkows.wms.goodsReceipt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.utils.MessageBox;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddItemsToListFragment extends Fragment {

    @BindView(R.id.clientTextView)
    TextView clientTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_items_good_receipt_fragment, container, false);

        ButterKnife.bind(this, view);
        String value = getArguments().getString("key");
        clientTextView.setText(value);

        return view;
    }
}
