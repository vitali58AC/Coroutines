package com.skillbox.github.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface GitHubApi {

    @GET("user")
    suspend fun getUser(
        @Header("Authorization") token: String
    ): User

    @GET("repositories")
    fun getRepositories(
        @Header("Authorization") token: String
    ): Call<List<Repositories>>

    @GET("user/starred/{owner}/{repo}")
    fun checkStarred(
        @Header("Authorization") token: String,
        @Path(value = "owner") owner: String,
        @Path(value = "repo") repo: String,
    ): Call<String>

    @PUT("user/starred/{owner}/{repo}")
    suspend fun putStarred(
        @Header("Authorization") token: String,
        @Path(value = "owner") owner: String,
        @Path(value = "repo") repo: String,
    ): Response<Unit>

    @DELETE("user/starred/{owner}/{repo}")
    suspend fun deleteStarred(
        @Header("Authorization") token: String,
        @Path(value = "owner") owner: String,
        @Path(value = "repo") repo: String,
    ): Response<Unit>

    @GET("user/starred")
    fun getStarred(
        @Header("Authorization") token: String
    ): Call<List<Repositories>>

    @PATCH("user")
    suspend fun patchName(
        @Header("Authorization") token: String,
        @Body name: PatchUser
    ): User

    @GET("user/following")
    suspend fun getFollowing(
        @Header("Authorization") token: String
    ): List<User>
}

