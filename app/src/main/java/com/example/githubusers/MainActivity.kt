package com.example.githubusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
        val edSearch = findViewById<EditText>(R.id.ed_search)
        val btnSearch = findViewById<Button>(R.id.btn_search)

        rvUsers?.layoutManager = LinearLayoutManager(this)
        rvUsers?.setHasFixedSize(true)

        fun getUsersList(username: String){
            val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.github.com")
            val endpoint = retrofitClient.create(Endpoint::class.java)

            endpoint.getUsers(username).enqueue(object : retrofit2.Callback<Users> {
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

                    adapterUser.clickUser =
                        object : AdapterUser.ClickUser {
                            override fun click(user: User) {

                                val nameUser = user.login
                                val photo = user.avatar_url

                                val i = Intent(this@MainActivity, UserDetails::class.java)
                                i.putExtra("name", nameUser)
                                i.putExtra("photo", photo)
                                startActivity(i)
                            }
                        }




                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    println("Erro")
                }

            })
        }

        btnSearch.setOnClickListener {

            if (edSearch.text.toString() != "")
                getUsersList(edSearch.text.toString())
        }

    }


}
