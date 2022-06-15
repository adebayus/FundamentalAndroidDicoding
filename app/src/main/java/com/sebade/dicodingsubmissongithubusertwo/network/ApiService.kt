package com.sebade.dicodingsubmissongithubusertwo.network

import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.ResponseDetailUserItem
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.ResponseSearchUser
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.UserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("users?per_page=10")
    fun getListUser(): Call<List<UserItem>>

    @Headers("Authorization: token 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")
    @GET("search/users")
    fun searchListUser(
        @Query("q") q: String,
        @Query("per_page") per_page: String = "10"
    ): Call<ResponseSearchUser>

    @Headers("Authorization: token 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")
    @GET("users/{username}")
    fun getDetail(
        @Path("username") username: String
    ): Call<ResponseDetailUserItem>

    @Headers("Authorization: token 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")
    @GET("users/{username}/followers?per_page=10")
    fun getListUserFollower(
        @Path("username") username: String
    ): Call<List<UserItem>>

    @Headers("Authorization: token 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")
    @GET("users/{username}/following?per_page=10")
    fun getListUserFollowing(
        @Path("username") username: String
    ): Call<List<UserItem>>
}