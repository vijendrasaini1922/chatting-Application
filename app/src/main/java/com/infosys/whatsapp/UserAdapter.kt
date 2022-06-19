package com.infosys.whatsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signup.view.*
import kotlinx.android.synthetic.main.user_layout.view.*

class UserAdapter(val context: Context, private val userList:ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    lateinit var userViewHolder: UserViewHolder

    class UserViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var textName: TextView = view.text_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        userViewHolder = UserViewHolder(view)
        return userViewHolder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var currentUser = userList[position]
        userViewHolder.textName.text = currentUser.name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name", currentUser.name)
            intent.putExtra("uid", currentUser.uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


}