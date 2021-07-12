package com.ihsan946.sholatq.api;

import com.ihsan946.sholatq.model.ApimodelJadwal;
import com.ihsan946.sholatq.model.DatetimeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServiceJadwal {
//    jadwal harian
    @GET("today.json?city={kota}&school=3")
    Call<ApimodelJadwal> getJadwal(@Path(value = "kota",encoded = true) String parameter) ;

}
