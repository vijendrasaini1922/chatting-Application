package com.infosys.whatsapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sent.*

class ChatActivity : AppCompatActivity() {

    private lateinit var messageList:ArrayList<message>
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")

        supportActionBar?.title = name

        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)
        chatRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        chatRecyclerView.adapter = messageAdapter


        mDatabase = FirebaseDatabase.getInstance().getReference()

        val receiverRoom: String
        val senderRoom: String

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid




        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid

        // Logic for adding data to activity chat
        mDatabase.child("chats").child(senderRoom).child("messages")
            .addValueEventListener(object : ValueEventListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for(postSnapshot in snapshot.children){
                        val textMessage = postSnapshot.getValue(message::class.java)
                        messageList.add(textMessage!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    // No need
                }

            })


        // Adding the message to the database
        sent_btn.setOnClickListener {
            val textMessage = message_box.text.toString()
            // creating message object using val message
            val messageObject = message(textMessage, senderUid)
            mDatabase.child("chats").child(senderRoom).child("messages").push().setValue(messageObject)
                .addOnSuccessListener {
                    mDatabase.child("chats").child(receiverRoom).child("messages").push().setValue(messageObject)
                }
            message_box.setText("")
        }

    }
}