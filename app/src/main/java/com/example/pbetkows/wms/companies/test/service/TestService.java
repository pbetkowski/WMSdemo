package com.example.pbetkows.wms.companies.test.service;


import com.example.pbetkows.wms.companies.test.model.Fuel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface TestService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("http://192.168.43.118:8080/rest/fuelAll/")
    Observable<List<Fuel>> getFuel();
}

