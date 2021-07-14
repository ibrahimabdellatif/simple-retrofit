package com.ibrahim.simpleretrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderAPI {
    //because we need to back list of json posts
    //this method don't have body so we add later (it's interface)
    @GET("posts")
    Call<List<Post>> getPost();

    /**
     * @Path() it is retrofit notation used to attach
     * specific id between {} with parameter in method
     * and you can pass it when you implement the method
     */
    //@GET("/posts/{id}/comments")
    //Call<List<Comments>> getComments(@Path("id") int postId);

    /**
     * @Query() it's retrofit notation used to passed specific variable
     * to query like this "https://jsonplaceholder.typicode.com/comments?postId=15"
     * so in this example it make postId= int postId >> ?postId=15
     */
    @GET("comments")
    Call<List<Comments>> getComments(
            /**
             * -this Query is provided by API it self not for all APIS
             * -use Integer instead of int because Integer is nullable value
             *  so if you not wanted to pass int value pass null
             *  -if you need more then one postId Query you shouldn't repeat Query
             *   instead of this you can passed Integer Array or List<Integer>
             *       or var args with three dots ... but put line at end of line
             */
            @Query("postId") Integer[] postId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("comments")
        //we can use QueryMap instead of Query to pass more parameter
        //map store in key and value (Map is interface)
    Call<List<Comments>> getComments(@QueryMap Map<String, String> parameters);

    //you can pass url of API directly
    @GET("comments")
    Call<List<Comments>> getComments(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    //by using @FormUrlEncoded notation you can passed value in main activity directly without need object
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    //@POST() in side this brackets it is end point of API so must be added
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String , String> fields);
}
