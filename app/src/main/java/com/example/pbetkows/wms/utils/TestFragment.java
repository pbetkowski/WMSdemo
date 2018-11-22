package com.example.pbetkows.wms.utils;

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

import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.companies.test.model.Fuel;
import com.example.pbetkows.wms.services.RetroFitService;
import com.example.pbetkows.wms.companies.test.service.TestService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TestFragment extends Fragment {

    @BindView(R.id.testList)
    ListView listView;

    List<String> result;

    private TestService testService;
    private ArrayAdapter adapter;
    private Observable<Fuel> wikiObservable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test, container, false);
        ButterKnife.bind(this, view);

        testService = RetroFitService.initializeTestSetcice();

        testService.getFuel();

        getClients();


        return view;

    }

    @SuppressLint("CheckResult")
    private void getClients() {
        result = new ArrayList<>();

        testService.getFuel()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        val -> {
                            wikiObservable = Observable.fromIterable(val);
                            wikiObservable.subscribe(n -> result.add(n.getTypeFuel()));

                        },
                        error -> MessageBox.show(getContext(), error.getMessage()),
                        () -> {
                            Collections.sort(result);
                            adapter = new ArrayAdapter(Objects.requireNonNull(getContext()),
                                    android.R.layout.simple_list_item_1, result);
                            listView.setAdapter(adapter);
                        },
                        d -> MessageBox.show(getContext(), "Connecting to API...")
                );
    }
}
