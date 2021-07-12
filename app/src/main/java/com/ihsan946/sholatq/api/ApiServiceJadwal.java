package com.ihsan946.sholatq.api;

import com.ihsan946.sholatq.model.ApimodelJadwal;
import com.ihsan946.sholatq.model.DatetimeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceJadwal {
//    jadwal harian
    @GET("v2/times/today.json")
    Call<ApimodelJadwal> getJadwal(@Query("ip") String ip);

}
