package com.example.pbetkows.wms.goodsReceipt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pbetkows.wms.MainMenuFragment;
import com.example.pbetkows.wms.R;
import com.example.pbetkows.wms.apiKeys.ApiKeys;
import com.example.pbetkows.wms.model.Wiki;
import com.example.pbetkows.wms.services.RetroFitService;
import com.example.pbetkows.wms.services.SampleService;
import com.example.pbetkows.wms.utils.MessageBox;
import com.example.pbetkows.wms.utils.Navigator;
import com.example.pbetkows.wms.utils.StaticGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class AddItemsToListFragment extends Fragment {

    View view;

    @BindView(R.id.clientTextView) TextView clientTextView;
    @BindView(R.id.documentNuberTextView) TextView documentNumberTextView;
    @BindView(R.id.docDateTextView) TextView docDateTextView;
    @BindView(R.id.itemList) ListView itemList;
    @BindView(R.id.addItemToGoodsReceipt) FloatingActionButton addItemButton;
    @BindView(R.id.saveGoodsReceipt) FloatingActionButton saveGoodsReceiptButton;


    private ZXingScannerView scannerView;
    private SampleService sampleService;
    private List<String> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_items_good_receipt_fragment, container, false);

        ButterKnife.bind(this, view);
        sampleService = RetroFitService.initializeSampleService();
        assert getArguments() != null;
        clientTextView.setText(getArguments().getString("key"));
        docDateTextView.setText(StaticGenerators.getCurrentDate());
        initializeAddButton();
        initializeSaveButton();

        return view;
    }

    private void addToList(String barcode) {
        items.add(barcode);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, items);
        itemList.setAdapter(adapter);
    }



    private void initializeSaveButton() {

        saveGoodsReceiptButton.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));

            builder.setPositiveButton("Save", (dialog, id) -> {
                Wiki wiki = new Wiki();
                wiki.setTitle(clientTextView.getText().toString() + " " + docDateTextView.getText().toString());
                StringBuilder sb = new StringBuilder();

//            for (String s : items) {
//                sb.append(s + " ");
//            }
                wiki.setContent("asd");
                wiki.setSlug(clientTextView.getText().toString() + " " + docDateTextView.getText().toString());
                sampleService.createPage(ApiKeys.API_KEY, ApiKeys.PROJECT_I2, wiki)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                m -> {
                                    saveGoodsReceiptButton.setEnabled(false);

                                },
                                error -> {
                                    MessageBox.show(getContext(), error.getMessage());
                                },
                                () -> {
                                    MessageBox.show(getContext(), "Saved in database");
                                    getActivity().setContentView(R.layout.activity_main);
                                    Navigator.navigate(getFragmentManager(), new MainMenuFragment());
                                }
                        );
            });
            builder.setNegativeButton("Cancel", (dialog, id) -> {
                MessageBox.show(getContext(), "Transaction cancelled");
            });
            builder.setTitle("Confirm transaction ?");
            builder.setIcon(R.drawable.ic_save_black_24dp);

            AlertDialog dialog = builder.create();
            dialog.show();


        });
    }

    private void initializeAddButton() {
        addItemButton.setOnClickListener(v -> {
            scannerView = new ZXingScannerView(getActivity());
            Objects.requireNonNull(getActivity()).setContentView(scannerView);
            scannerView.setResultHandler(result -> {
                MessageBox.show(getContext(), result.getText());
                addToList(result.getText());
                scannerView.stopCamera();
                //todo generuje błędy
                if (view.getParent() != null)
                    ((ViewGroup) view.getParent()).removeView(view); // <- fix
                getActivity().setContentView(view);
            });
            scannerView.startCamera();
        });
    }
}
