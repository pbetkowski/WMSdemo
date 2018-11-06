package com.example.pbetkows.wms.goodsReceipt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.services.GoodsReceiptNService;
import com.example.pbetkows.wms.services.RetroFitService;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class GoodsReceipt extends Fragment implements RetroFitService {

    @BindView(R.id.list) ListView listView;

    private List<String> result;
    private GoodsReceiptNService goodsReceiptNService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_receipt_n, container, false);
        ButterKnife.bind(this, view);
        result = new ArrayList<>();
        initializeRetrofit();

        return view;
    }

    @Override
    public void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/pos/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        goodsReceiptNService = retrofit.create(GoodsReceiptNService.class);
        Log.d("TAG", "TEST");
    }


//    private void getData() {
//        goodsReceiptNService.getPositions()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(
//                        v -> {
//                            Log.d("TAG", v.toString());
//                            for (GoodsReceiptN w : v) {
//                                result.add(w.getIndeks());
//                            }
//                        },
//                        err -> {
//                            MessageBox.show(getContext() ,err.getMessage());
//                            Log.d("TAG", err.getMessage());
//                        },
//
//                        () -> {
//                            Log.d("TAG", "Finish getAll");
//                            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, result);
//                            listView.setAdapter(adapter);
//                        },
//                        d -> {
//                            Log.d("TAG", "Finished");
//                        }
//                );
//    }
}
