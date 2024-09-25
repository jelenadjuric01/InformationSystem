/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client.rest;

/**
 *
 * @author Jelena
 */
import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {
    @GET("user")
    Call<DataResponse<List<User>>> users();

    @POST("user")
    @FormUrlEncoded
    Call<DataResponse<User>> createUser(
            @Field("townId") int townId, @Field("name") String name,
            @Field("lastname") String lastname,
            @Field("email") String email, @Field("year") int year,
            @Field("sex") Character sex);

   
    @GET("town")
    Call<DataResponse<List<Town>>> towns();

    @GET("category")
    Call<DataResponse<List<Category>>> categories();
    
    @GET("video")
    Call<DataResponse<List<Video>>> videos();
    
    @GET("package")
    Call<DataResponse<List<Package>>> packages();
    
    @GET("category/{videoId}")
    Call<DataResponse<List<Category>>> categoriesOfVideo(@Path("videoId") int videoId);

     @GET("subscription/{userId}")
    Call<DataResponse<List<Subscription>>> subsOfUser(@Path("userId") int userId);

    @GET("watched/{videoId}")
    Call<DataResponse<List<Watched>>> watchesOfVideo(@Path("videoId") int videoId);

     @GET("rating/{videoId}")
    Call<DataResponse<List<Rating>>> ratingsOfVideo(@Path("videoId") int videoId);

    
    
    @PATCH("user/{userId}")
    @FormUrlEncoded
    Call<DataResponse<User>> updateUsersEmail(@Path("userId") int userId,
            @Field("email") String email);
    
    @PATCH("user/town/{userId}")
    @FormUrlEncoded
    Call<DataResponse<User>> updateUsersTown(@Path("userId") int userId,
            @Field("townId") int townId);
    
   
    @POST("category")
    @FormUrlEncoded
    Call<DataResponse<Category>> createCategory(@Field("name") String name);
    
   
    @POST("town")
    @FormUrlEncoded
    Call<DataResponse<Town>> createTown(@Field("name") String name);
    
  
    
    @POST("video")
    @FormUrlEncoded
    Call<DataResponse<Video>> createVideo(
            @Field("userId") int userId,
            @Field("name") String name,
            @Field("duration") int duration);

    @PATCH("video/{videoId}")
    @FormUrlEncoded
    Call<DataResponse<Video>> updateVideosName(@Path("videoId") int videoId,
            @Field("name") String name);
    
    @POST("video/{videoId}")
    @FormUrlEncoded
    Call<DataResponse<Belongs>> createBelongs(
            @Path("videoId") int videoId,
            @Field("catId") int catId);

    @POST("package")
    @FormUrlEncoded
    Call<DataResponse<Package>> createPackage(
            @Field("price") int price
            );
    @PATCH("package/{packageId}")
    @FormUrlEncoded
    Call<DataResponse<Package>> updatePackagesPrice(@Path("packageId") int packageId,
            @Field("price") int price);
    
    @POST("subscription")
    @FormUrlEncoded
    Call<DataResponse<Subscription>> createSub(
            @Field("packageId") int  packageId,
            @Field("userId") int userId
            );
    
    @POST("watched")
    @FormUrlEncoded
    Call<DataResponse<Watched>> createWatched(
            @Field("start") int start,@Field("seconds") int  seconds,
            @Field("userId") int userId,@Field("videoId") int videoId
            );
    
    @POST("rating")
    @FormUrlEncoded
    Call<DataResponse<Rating>> createRating(
            @Field("rate") int rate,
            @Field("userId") int userId,@Field("videoId") int videoId
            );
    
    @PATCH("rating/{ratingId}")
    @FormUrlEncoded
    Call<DataResponse<Rating>> updateRatingsRate(@Path("ratingId") int ratingId,
            @Field("rate") int rate);
    
    @DELETE("rating/{ratingId}")
    Call<Response> deleteRating(@Path("ratingId") int ratingId);

    @DELETE("video/{videoId}/{userId}")
    Call<Response> deleteVideo(@Path("videoId") int videoId,@Path("userId") int userId);
    
    

}
