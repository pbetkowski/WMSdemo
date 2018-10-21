package com.example.pbetkows.wms.tests;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pbetkows.wms.MainActivity;
import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.model.Sample;
import com.example.pbetkows.wms.services.SampleService;
import com.example.pbetkows.wms.services.RXService;
import com.example.pbetkows.wms.utils.MessageBox;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SampleList extends Fragment implements RXService {

    private List<String> result;
    private ListView listView;
    private SampleService sampleService;
    private FloatingActionButton returnButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sample_list, container, false);

        listView = view.findViewById(R.id.list);
        result = new ArrayList<>();
        returnButton = view.findViewById(R.id.returnButton1);
        initializeRXToList();
        getData();


        returnButton.setOnClickListener(v -> {
            ((MainActivity)getActivity()).setViewPager(1);
        });

        return view;
    }

    private void getData() {
        sampleService.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        v -> {
                            //Log.d("TAG", v.toString());
                            for (Sample w : v) {
                                result.add(w.getTitle());
                            }
                        },
                        err -> {
                            MessageBox.Show(getContext() ,err.getMessage());
                        },

                        () -> {
                            Log.d("TAG", "Finish getAll");
                            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, result);
                            listView.setAdapter(adapter);
                        },
                        d -> {
                            Log.d("TAG", "Finished");
                        }
                );
    }

    @Override
    public void initializeRXToList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/posts/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        sampleService = retrofit.create(SampleService.class);
        Log.d("TAG", "TEST");
    }
}


