package com.adi.githubsearch.networking

import com.adi.githubsearch.models.ModelSearch
import com.adi.githubsearch.models.ModelSearchData
import com.adi.githubsearch.models.ModelUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<ModelSearch>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<ModelUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowerUser(
        @Path("username") username: String
    ): Call<ArrayList<ModelSearchData>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUser(
        @Path("username") username: String
    ): Call<ArrayList<ModelSearchData>>
}