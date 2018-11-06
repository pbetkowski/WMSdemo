package com.example.pbetkows.wms.goodsReceipt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.apiKeys.ApiKeys;
import com.example.pbetkows.wms.model.Wiki;
import com.example.pbetkows.wms.services.RetroFitService;
import com.example.pbetkows.wms.services.SampleService;
import com.example.pbetkows.wms.utils.MessageBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class ChooseClientFragment extends Fragment implements RetroFitService {

    @BindView(R.id.client_list_goodsReceipt) ListView listView;
    @BindView(R.id.chooseClient) SearchView chooseClient;


    private List<String> supplierList;
    private SampleService sampleService;
    private AddItemsToListFragment chooseClientFragment = new AddItemsToListFragment();

    private Bundle args = new Bundle();
    private ArrayAdapter adapter;
    private Observable<Wiki> wikiObservable;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chose_client_fragment_goods_receipt, container, false);
        ButterKnife.bind(this, view);

        initializeListeners();
        initializeRetrofit();
        getClients();

        return view;
    }

    @Override
    public void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gitlab.com/api/v4/projects/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        sampleService = retrofit.create(SampleService.class);
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
                            adapter = new ArrayAdapter(getContext(),
                                    android.R.layout.simple_list_item_1, supplierList);
                            listView.setAdapter(adapter);
                        },
                        d -> MessageBox.show(getContext(), "Connecting to API...")
                );
    }

    private void navigate(Fragment fragment) {
        if (getFragmentManager() != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
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
            chooseClientFragment.setArguments(args);
            navigate(chooseClientFragment);
        });
    }
}
