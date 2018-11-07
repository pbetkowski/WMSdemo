package com.example.pbetkows.wms.services;


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

}
