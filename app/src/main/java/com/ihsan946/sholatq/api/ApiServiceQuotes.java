//NIM : 10118037
//NAMA : MUHAMMAD IHSAN
//KELAS : IF-1 2018
//Tanggal Pembuatan : 28 Juni 2021

package com.ihsan946.sholatq.api;

import com.ihsan946.sholatq.model.ApimodelQuotes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceQuotes {

    @GET("/api/data/quotes")
    Call<ApimodelQuotes> getQuotes();
}
