package com.ibrahim.simpleretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        //getPost();
        //getComments();
        //createPost();
        //updatePost();
        deletePost();
    }

    private void getPost() {

        Call<List<Post>> call = jsonPlaceHolderAPI.getPost();
        //it execute in background
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //check if response is not successful
                if (!response.isSuccessful()) {
                    tvResult.setText("code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "UserId: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    tvResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }


    private void getComments() {
        /**
         * if you don't need to pass this parameter use null instead of string value
         *  to use null for use Integer not int "new Integer[]{5, 8, 10}, "id", "desc""
         */

        Map<String, String> parameters = new HashMap<>();
        parameters.put("postId", "5");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<Comments>> callComments = jsonPlaceHolderAPI.getComments(parameters);
        callComments.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText("code:" + response.code());
                    return;
                }
                List<Comments> comment = response.body();

                for (Comments comments : comment) {
                    String content = "";
                    content += "postId: " + comments.getPostId() + "\n";
                    content += "id: " + comments.getId() + "\n";
                    content += "name: " + comments.getName() + "\n";
                    content += "email: " + comments.getEmail() + "\n";
                    content += "body: " + comments.getBody() + "\n\n";
                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
        Post post = new Post(22, "New Title", "New Text");
        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "New Title");
        //we pass value directly because we used @FromUrlEncoded with @Field
        Call<Post> call = jsonPlaceHolderAPI.createPost(fields);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText("code: " + response.code());
                    return;
                }
                Post postResponse = response.body();

                String content = "";
                content += "Code:" + response.code() + "\n";
                content += "id: " + postResponse.getId() + "\n";
                content += "UserId: " + postResponse.getUserId() + "\n";
                content += "title: " + postResponse.getTitle() + "\n";
                content += "text: " + postResponse.getText() + "\n\n";
                tvResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResult.setText(t.getMessage());
                //
            }
        });
    }

    private void updatePost() {
        Post post = new Post(12, null, "New Text");
        Call<Post> call = jsonPlaceHolderAPI.patchPost(5, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText("code: " + response.code());
                    return;
                }
                Post postResponse = response.body();

                String content = "";
                content += "Code:" + response.code() + "\n";
                content += "id: " + postResponse.getId() + "\n";
                content += "UserId: " + postResponse.getUserId() + "\n";
                content += "title: " + postResponse.getTitle() + "\n";
                content += "text: " + postResponse.getText() + "\n\n";
                tvResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }
    private void deletePost(){
        Call<Void> call = jsonPlaceHolderAPI.deletePost(5);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                tvResult.setText("code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }
}