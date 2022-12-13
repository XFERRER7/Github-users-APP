package com.example.githubusers.api

import com.example.githubusers.model.SearchedUser
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
    fun getDetailUser(@Path(value = "username", encoded = true) username: String) : Call<SearchedUser>

}


//Search : https://api.github.com/search/users?q={username}

//Detail user : https://api.github.com/users/{username}