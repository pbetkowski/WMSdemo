//package com.example.pbetkows.wms.utils;
//
//import com.example.pbetkows.wms.services.RetroFitService;
//import com.example.pbetkows.wms.services.SampleService;
//
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.jackson.JacksonConverterFactory;
//
//public class RXGenerator {
//
//    public static Class initializeRetrofit(String url, Interfa rxService) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(JacksonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        rxService = retrofit.create(Class.class);
//        return rxService;
//    }
//}
