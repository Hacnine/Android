package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface JsonplaceholderApi {
    @GET("posts")
    Call<List<Post>>getPost(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    //Using three dots
    @GET("posts")
    Call<List<Post>>getPost(
            @Query("_sort") String sort,
            @Query("_order") String order,
            @Query("userId") Integer... userId
    );


/*

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComment(@Path("id") int postId);
 */


    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );
}
