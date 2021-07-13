package com.ihsan946.sholatq.model;

import com.google.gson.annotations.SerializedName;

public class ApimodelLokasi {

//
//    //   get lokasi pengguna
    @SerializedName("status")
    public String status;
    @SerializedName("query")
    public String query;
    @SerializedName("hasil")
    public String hasil;

    public String getHasil() {
        return hasil;
    }
//
//    //
}
