package com.example.githubusers.api

import com.example.githubusers.model.Users
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {

    @GET("/search/users")
    fun getUsers(@Query("q") username : String) : Call<Users>

    @GET("/users/{username}")
    fun getDetailUser(@Path(value = "username", encoded = true) username: String) : Call<JsonObject>

    @GET("/users/{username}/followers")
    fun getListFollower(@Path(value = "username", encoded = true) username: String) : Call<JsonObject>

    @GET("/users/{username}/following")
    fun getListFollowing(@Path(value = "username", encoded = true) username: String) : Call<JsonObject>

}


//Search : https://api.github.com/search/users?q={username}
//
//Detail user : https://api.github.com/users/{username}
//
//List Follower : https://api.github.com/users/{username}/followers
//
//List Following : https://api.github.com/users/{username}/following