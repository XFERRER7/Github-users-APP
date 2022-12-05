package com.example.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.adapter.AdapterUser
import com.example.githubusers.api.Endpoint
import com.example.githubusers.model.User
import com.example.githubusers.model.Users
import com.example.githubusers.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvUsers = findViewById<RecyclerView>(R.id.rv_users)

        rvUsers?.layoutManager = LinearLayoutManager(this)
        rvUsers?.setHasFixedSize(true)


        fun getUsersList(){
            val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com")
            val endpoint = retrofitClient.create(Endpoint::class.java)

            endpoint.getUsers("mariana").enqueue(object : retrofit2.Callback<Users> {
                override fun onResponse(call: Call<Users>, response: Response<Users>) {

                    val responseBody: Users? = response.body()

                    var users: Users? = null

                    responseBody?.let {
                        users = it
                    }

                    var list = mutableListOf<User>()

                    users?.users?.forEach{
                        list.add(it)
                    }

                    val adapterUser = AdapterUser(list)

                    rvUsers?.adapter = adapterUser


                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    println("Não foi")
                }

            })
        }

        getUsersList()
    }


}
