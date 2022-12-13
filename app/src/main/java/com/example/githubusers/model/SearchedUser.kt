package com.example.githubusers.model

import com.google.gson.annotations.SerializedName

data class SearchedUser(


    @SerializedName("public_repos")
    val publicRepos: String,

    @SerializedName("followers")
    val followers : String,

    @SerializedName("following")
    val following : String,

)
