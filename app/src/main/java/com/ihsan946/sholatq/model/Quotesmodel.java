package com.ihsan946.sholatq.model;

import com.google.gson.annotations.SerializedName;

public class Quotesmodel {

@SerializedName("text_en")
    public String text_en;
@SerializedName("text_id")
    public String text_quotes;
@SerializedName("author")
    public String author;

    public String getText_en() {
        return text_en;
    }

    public String getText_quotes() {
        return text_quotes;
    }

    public String getAuthor() {
        return author;
    }
}
