package com.ibrahim.simpleretrofit;

import com.google.gson.annotations.SerializedName;

/**
 * this is first class for Json attributes.
 */
public class Post {
    private int userId;
    private int id;
    private String title;
    @SerializedName("body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
