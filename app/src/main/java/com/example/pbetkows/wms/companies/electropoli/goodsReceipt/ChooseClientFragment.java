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
import com.example.pbetkows.wms.utils.Navigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ChooseClientFragment extends Fragment {

    @BindView(R.id.client_list_goodsReceipt) ListView listView;
    @BindView(R.id.chooseClient) SearchView chooseClient;


    private List<String> supplierList;
    private SampleService sampleService;
    private AddItemsToListFragment addItemsToListFragment = new AddItemsToListFragment();

    private Bundle args = new Bundle();
    private ArrayAdapter adapter;
    private Observable<Wiki> wikiObservable;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chose_client_fragment_goods_receipt, container, false);
        ButterKnife.bind(this, view);
        sampleService = RetroFitService.initializeSampleService();
        initializeListeners();
        getClients();

        return view;
    }

    @SuppressLint("CheckResult")
    private void getClients() {
        supplierList = new ArrayList<>();

        sampleService.getAll(ApiKeys.API_KEY, ApiKeys.PROJECT_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        val -> {
                            wikiObservable = Observable.fromIterable(val);
                            wikiObservable.subscribe(n -> supplierList.add(n.getSlug()));

                        },
                        error -> MessageBox.show(getContext(), error.getMessage()),
                        () -> {
                            Collections.sort(supplierList);
                            adapter = new ArrayAdapter(Objects.requireNonNull(getContext()),
                                    android.R.layout.simple_list_item_1, supplierList);
                            listView.setAdapter(adapter);
                        },
                        d -> MessageBox.show(getContext(), "Connecting to API...")
                );
    }


    private void initializeListeners() {
        chooseClient.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        listView.setOnItemClickListener((a, b, c, d) -> {
            args.putString("key", listView.getAdapter().getItem(c).toString());
            addItemsToListFragment.setArguments(args);
            Navigator.navigate(getFragmentManager(), addItemsToListFragment);
        });
    }
}
