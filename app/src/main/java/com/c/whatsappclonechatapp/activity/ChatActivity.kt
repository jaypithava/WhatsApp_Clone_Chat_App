package com.c.whatsappclonechatapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import com.c.whatsappclonechatapp.R
import com.c.whatsappclonechatapp.databinding.ActivityChatBinding
import com.c.whatsappclonechatapp.model.MessageModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var database: FirebaseDatabase

    private lateinit var senderUid: String
    private lateinit var receiverUid: String

    private lateinit var senderRoom: String
    private lateinit var receiverRoom: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        senderUid = FirebaseAuth.getInstance().uid.toString()

        receiverUid = intent.getStringExtra("uid")!!

        senderRoom = senderUid + receiverUid

        receiverRoom = receiverUid + senderUid

        database = FirebaseDatabase.getInstance()

        binding.imageSend.setOnClickListener {
            if (binding.messageBox.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Message", Toast.LENGTH_SHORT).show()
            } else {
                val message =
                    MessageModel(binding.messageBox.text.toString(), senderUid, Date().time)

                val randomKey = database.reference.push().key
                database.reference.child("chats").child(senderRoom).child("message")
                    .child(randomKey!!).setValue(message)
                    .addOnSuccessListener {
                        database.reference.child("chats").child(receiverRoom).child("message")
                            .child(randomKey).setValue(message)
                            .addOnSuccessListener {
                                binding.messageBox.text=null
                                Toast.makeText(this, "Message Sent!!", Toast.LENGTH_SHORT).show()
                            }
                    }
            }
        }
    }
}