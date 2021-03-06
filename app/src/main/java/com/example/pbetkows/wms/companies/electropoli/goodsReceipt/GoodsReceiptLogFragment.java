package com.example.pbetkows.wms.companies.electropoli.goodsReceipt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.apiKeys.ApiKeys;
import com.example.pbetkows.wms.companies.test.model.Wiki;
import com.example.pbetkows.wms.services.RetroFitService;
import com.example.pbetkows.wms.companies.test.service.SampleService;
import com.example.pbetkows.wms.utils.MessageBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GoodsReceiptLogFragment extends Fragment {

    View view;

    @BindView(R.id.createdGoodsReceiptList)  ListView createdGoodsReceiptList;
    @BindView(R.id.searchDocument) SearchView searchEditText;

    private  SampleService sampleService;
    private List<String> result;
    private Observable<Wiki> wikiObservable;
    private ArrayAdapter mainAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.goods_receipt_log, container, false);
        sampleService = RetroFitService.initializeSampleService();
        ButterKnife.bind(this, view);
        getData();
        searchEditText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mainAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return view;
    }




    @SuppressLint("CheckResult")
    private void getData() {
        result = new ArrayList<>();
        sampleService.getAll(ApiKeys.API_KEY, ApiKeys.PROJECT_I2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        val -> {
                            wikiObservable = Observable.fromIterable(val);
                            wikiObservable.subscribe(v -> result.add(v.getSlug()));
                        },
                        error -> MessageBox.show(getContext(), error.getMessage()),
                        () -> {
                            Collections.sort(result);
                            mainAdapter = new ArrayAdapter(getContext(),
                                    android.R.layout.simple_list_item_1, result);
                            createdGoodsReceiptList.setAdapter(mainAdapter);

                        },
                        d -> MessageBox.show(getContext(), "Connecting to API...")
                );
    }

}
