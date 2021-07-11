package com.ihsan946.sholatq.api;

import com.ihsan946.sholatq.model.Apimodel;
import com.ihsan946.sholatq.model.Sholatqmodel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiServiceLokasi {

    //
    @GET("/domain/geoip/{ip}")
    Call<Apimodel> getLokasi(@Path(value = "ip",encoded = true) String parameter);

//
}
