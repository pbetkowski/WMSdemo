package com.example.pbetkows.wms.services;

import com.example.pbetkows.wms.model.Sample;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface SampleService  {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("https://jsonplaceholder.typicode.com/posts")
    Observable<List<Sample>> getAll();
}
