package com.example.githubusers.model

import com.google.gson.annotations.SerializedName

data class Users(


    @SerializedName("items")
    val users: List<User>? = null
)
