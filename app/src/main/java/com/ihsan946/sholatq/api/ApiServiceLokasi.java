package com.ihsan946.sholatq.api;

import com.ihsan946.sholatq.model.ApimodelLokasi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceLokasi {

    //
    @GET("/json")
    Call<ApimodelLokasi> getLokasi();

//
}
