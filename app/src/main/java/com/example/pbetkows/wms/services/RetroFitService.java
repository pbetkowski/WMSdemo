package com.example.pbetkows.wms.services;


import com.example.pbetkows.wms.companies.test.service.SampleService;
import com.example.pbetkows.wms.companies.test.service.TestService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetroFitService {

    public static SampleService initializeSampleService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gitlab.com/api/v4/projects/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(SampleService.class);
    }

    public static TestService initializeTestSetcice() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.118:8080/rest/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(TestService.class);
    }

}
