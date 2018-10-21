package com.example.pbetkows.wms.services;

import com.example.pbetkows.wms.model.GoodsReceiptN;
import com.example.pbetkows.wms.model.Sample;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GoodsReceiptNService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("http://192.168.0.12:8080/api/pos/")
    Observable<List<GoodsReceiptN>> getPositions();
}
