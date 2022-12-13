package com.example.githubusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.githubusers.api.Endpoint
import com.example.githubusers.model.SearchedUser
import com.example.githubusers.model.Users
import com.example.githubusers.utils.NetworkUtils
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

class UserDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val tvUsername = findViewById<TextView>(R.id.tv_username)
        val imgUser = findViewById<ImageView>(R.id.img_user)
        val tvFollowing = findViewById<TextView>(R.id.tv_following)
        val tvFollowers = findViewById<TextView>(R.id.tv_followers)
        val tvRepos = findViewById<TextView>(R.id.tv_repos)

        val data = intent.extras
        val name = data?.getString("name")
        val photo = data?.getString("photo")

        Picasso.get().load(photo).into(imgUser)
        tvUsername.text = name



        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        if (name != null) {
            endpoint.getDetailUser(name).enqueue(object : retrofit2.Callback<SearchedUser> {

                override fun onResponse(call: Call<SearchedUser>, response: Response<SearchedUser>) {
                    val responseBody: SearchedUser? = response.body()

                    tvFollowing.text = responseBody?.following
                    tvFollowers.text = responseBody?.followers
                    tvRepos.text = responseBody?.publicRepos
                }

                override fun onFailure(call: Call<SearchedUser>, t: Throwable) {
                    println("Erro")
                }

            })
        }







    }
}