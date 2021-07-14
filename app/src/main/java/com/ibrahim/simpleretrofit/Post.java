package com.ibrahim.simpleretrofit;

import com.google.gson.annotations.SerializedName;

/**
 * this is first class for Json attributes.
 */
public class Post {
    private int userId;
    private Integer id;
    private String title;
    @SerializedName("body")
    private String text;

    //constructor for @POST retrofit , we not pass id because it generated auto by retrofit
    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
