package com.example.pbetkows.wms.goodsReceipt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.apiKeys.ApiKeys;
import com.example.pbetkows.wms.model.Sample;
import com.example.pbetkows.wms.model.Wiki;
import com.example.pbetkows.wms.services.RXService;
import com.example.pbetkows.wms.services.SampleService;
import com.example.pbetkows.wms.utils.MessageBox;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class ChooseClientFragment extends Fragment implements RXService {

    @BindView(R.id.client_list_goodsReceipt)
    ListView listView;

    private List<String> result;
    SampleService sampleService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chose_client_fragment_goods_receipt, container, false);
        ButterKnife.bind(this, view);

        initializeRXToList();
        getClients();

        return view;
    }

    @Override
    public void initializeRXToList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gitlab.com/api/v4/projects/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        sampleService = retrofit.create(SampleService.class);
    }

    private void getClients() {
        result = new ArrayList<>();
        sampleService.getAll(ApiKeys.API_KEY, ApiKeys.PROJECT_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        val -> {
                            MessageBox.Show(getContext(), "Connecting to API...");
                            for (Wiki v : val) {
                                result.add(v.getSlug());
                            }
                        },
                        error -> {
                            MessageBox.Show(getContext(), error.getMessage());
                        },
                        () -> {
                            ArrayAdapter adapter = new ArrayAdapter(getContext(),
                                    android.R.layout.simple_list_item_1, result);
                            listView.setAdapter(adapter);
                        },
                        d -> {
                            Log.d(TAG, "subscribe getAll");
                        }
                );
    }
}