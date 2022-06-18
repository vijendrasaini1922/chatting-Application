package com.infosys.whatsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.receive.view.*
import kotlinx.android.synthetic.main.sent.view.*

class MessageAdapter(private val ctx: Context, private val messageList: ArrayList<message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemReceive = 1
    private val itemSent = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 1){
            // Inflate Receive
            val myView = LayoutInflater.from(ctx).inflate(R.layout.receive, parent, false)
            ReceiveViewHolder(myView)
        }else{
            // Inflate Sent
            val myView = LayoutInflater.from(ctx).inflate(R.layout.sent, parent, false)
            SentViewHolder(myView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if (holder.javaClass == SentViewHolder::class.java) {
            val viewHolder = holder as SentViewHolder
            viewHolder.sentMessage.text = currentMessage.message

        } else {
            val viewHolder = holder as ReceiveViewHolder
            viewHolder.receiveMessage.text = currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        return if(FirebaseAuth.getInstance().currentUser?.uid == currentMessage.senderId){
            itemSent
        }else{
            itemReceive
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sentMessage: TextView = itemView.sent_message
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receiveMessage: TextView = itemView.receive_message
    }
}