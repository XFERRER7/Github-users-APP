package com.example.githubusers.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.R
import com.example.githubusers.model.User
import com.example.githubusers.model.Users
import com.squareup.picasso.Picasso
import retrofit2.Callback

class AdapterUser(
    private val users: MutableList<User>,
    var clickUser: ClickUser =
        object : ClickUser {
            override fun click(user: User) {

            }
        }
    ): RecyclerView.Adapter<AdapterUser.UserViewHolder>() {



    interface ClickUser {
        fun click(user: User)
    }

    // Ligar variáveis com os eleementos do layout
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val login = itemView.findViewById<TextView>(R.id.name_user)
        val avatar_url = itemView.findViewById<ImageView>(R.id.img_user)


        private lateinit var user: User

        init {
            itemView.setOnClickListener {
                if(::user.isInitialized) {
                    Log.i("ListaUsersAdapter", "Item clicado")
                    clickUser.click(user)
                }
            }


        }

        fun vincula(user: User) {
            this.user = user
        }
    }





    // Informa qual layout é dos itens
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val itemList = LayoutInflater.from(parent.context).inflate(R.layout.card_user, parent, false)
        val holder = UserViewHolder(itemList)
        return holder

    }

    // Inserir as informações no layout
    override fun onBindViewHolder(holder: UserViewHolder, i: Int) {

        holder.login.text = users[i].login

        Picasso.get().load(users[i].avatar_url).into(holder.avatar_url);

        val user = users[i]
        holder.vincula(user)

    }

    override fun getItemCount(): Int = users.size


}
