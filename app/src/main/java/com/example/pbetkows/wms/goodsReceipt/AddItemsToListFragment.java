package com.example.pbetkows.wms.goodsReceipt;

import android.graphics.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pbetkows.wms.MainMenuFragment;
import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.apiKeys.ApiKeys;
import com.example.pbetkows.wms.model.Wiki;
import com.example.pbetkows.wms.services.RXService;
import com.example.pbetkows.wms.services.SampleService;
import com.example.pbetkows.wms.utils.MessageBox;
import com.example.pbetkows.wms.utils.StaticGenerators;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class AddItemsToListFragment extends Fragment implements RXService {

    @BindView(R.id.clientTextView) TextView clientTextView;
    @BindView(R.id.documentNuberTextView) TextView documentNumberTextView;
    @BindView(R.id.docDateTextView) TextView docDateTextView;
    @BindView(R.id.itemList) ListView itemList;
    @BindView(R.id.addItemToGoodsReceipt) FloatingActionButton addItemButton;
    @BindView(R.id.saveGoodsReceipt) FloatingActionButton saveGoodsReceiptButton;

    View view;
    private ZXingScannerView scannerView;
    private SampleService sampleService;

    List<String> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_items_good_receipt_fragment, container, false);

        ButterKnife.bind(this, view);
        clientTextView.setText(getArguments().getString("key"));
        docDateTextView.setText(StaticGenerators.getCurrentDate());
        initializeRXToList();

        addItemButton.setOnClickListener(v -> {
            scannerView = new ZXingScannerView(getActivity());
            getActivity().setContentView(scannerView);
            scannerView.setResultHandler(result -> {
                MessageBox.Show(getContext(), result.getText());
                addToList(result.getText());
                scannerView.stopCamera();
                //todo generuje błędy
                if(view.getParent()!=null)
                    ((ViewGroup)view.getParent()).removeView(view); // <- fix
                getActivity().setContentView(view);
            });
            scannerView.startCamera();
        });



        saveGoodsReceiptButton.setOnClickListener(v -> {

            Wiki wiki = new Wiki();
            wiki.setTitle(clientTextView.getText().toString() + " "+docDateTextView.getText().toString());
            StringBuilder sb = new StringBuilder();

//            for (String s : items) {
//                sb.append(s + " ");
//            }
            wiki.setContent("asd");
            wiki.setSlug(clientTextView.getText().toString() + " "+docDateTextView.getText().toString());
            sampleService.createPage(ApiKeys.API_KEY, ApiKeys.PROJECT_I2, wiki)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            m -> {

                                saveGoodsReceiptButton.setEnabled(false);
                                //navigate(new MainMenuFragment());
                            },
                            error -> {
                                MessageBox.Show(getContext(), error.getMessage());
                            },
                            () ->  MessageBox.Show(getContext(), "Saved in database")
                    );
        });



        return view;
    }

    private void addToList(String barcode) {
        items.add(barcode);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        itemList.setAdapter(adapter);
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

    private void navigate(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
