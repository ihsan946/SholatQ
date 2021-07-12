package com.ihsan946.sholatq.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DatetimeModel {

    @SerializedName("datetime")
    public List<times2Model> datetime = new ArrayList<>();

    public List<times2Model> getDatetime() {
        return datetime;
    }
}
