//NIM : 10118037
//NAMA : MUHAMMAD IHSAN
//KELAS : IF-1 2018
//Tanggal Pembuatan : 28 Juni 2021

package com.ihsan946.sholatq.api;

import com.ihsan946.sholatq.model.ApimodelLokasi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceLokasi {

    //
    @GET("/?token=2d8abdfc8dd985")
    Call<ApimodelLokasi> getLokasi();

//
}
