package com.ibrahim.simpleretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {
    //because we need to back list of json posts
    //this method don't have body so we add later (it's interface)
    @GET("posts")
    Call<List<Post>> getPost();
}
