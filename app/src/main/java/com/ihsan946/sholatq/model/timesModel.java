package com.ihsan946.sholatq.model;

import com.google.gson.annotations.SerializedName;

public class timesModel {

    @SerializedName("Fajr")
    public String shubuh;
    @SerializedName("Dhuhr")
    public String dzuhur;
    @SerializedName("Asr")
    public String asr;
    @SerializedName("Maghrib")
    public String maghrib;
    @SerializedName("Isha")
    public String isya;
}
