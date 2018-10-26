package com.example.pbetkows.wms.services;

import com.example.pbetkows.wms.model.Wiki;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SampleService  {




    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("{projectId}/wikis?with_content=1")
    Observable<List<Wiki>> getAll(@Header("PRIVATE-TOKEN") String token, @Path("projectId") int projectId);

    @POST("{projectId}/wikis")
    Observable<Wiki> createPage(@Header("PRIVATE-TOKEN") String token, @Path("projectId") int projectId, @Body Wiki wiki);
}
