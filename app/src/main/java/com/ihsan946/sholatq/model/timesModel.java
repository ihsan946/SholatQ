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

    public String getShubuh() {
        return shubuh;
    }

    public String getDzuhur() {
        return dzuhur;
    }

    public String getAsr() {
        return asr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public String getIsya() {
        return isya;
    }
}
