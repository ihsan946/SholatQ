package com.ihsan946.sholatq.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApimodelJadwal {

    @SerializedName("code")
    public int code;
    @SerializedName("status")
    public String status;
    @SerializedName("result")
    public List<DatetimeModel> result = new ArrayList<>();
}
