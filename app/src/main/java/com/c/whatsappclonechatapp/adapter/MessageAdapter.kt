package com.c.whatsappclonechatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.c.whatsappclonechatapp.R
import com.c.whatsappclonechatapp.databinding.ReceiverItemLayoutBinding
import com.c.whatsappclonechatapp.databinding.SentItemLayoutBinding
import com.c.whatsappclonechatapp.model.MessageModel
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(var context: Context, val list: ArrayList<MessageModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var itemSent = 1
    var itemReceive = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == itemSent)
            SentViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.sent_item_layout, parent, false)
            )
        else
            ReceiverViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.receiver_item_layout, parent, false)
            )
    }

    //Item Layout Set
    override fun getItemViewType(position: Int): Int {
        return if (FirebaseAuth.getInstance().uid == list[position].senderId) itemSent else itemReceive
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var message = list[position]
        if (holder.itemViewType == itemSent) {
            var viewHolder = holder as SentViewHolder
            viewHolder.binding.sentMessage.text = message.message
        } else {
            var viewHolder = holder as ReceiverViewHolder
            viewHolder.binding.receiveMessage.text = message.message
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class SentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: SentItemLayoutBinding = SentItemLayoutBinding.bind(view)
    }

    inner class ReceiverViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ReceiverItemLayoutBinding = ReceiverItemLayoutBinding.bind(view)
    }

}