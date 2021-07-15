package com.ihsan946.sholatq.api;

import com.ihsan946.sholatq.model.ApimodelQuotes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceQuotes {

    @GET("/api/data/quotes")
    Call<ApimodelQuotes> getQuotes();
}
