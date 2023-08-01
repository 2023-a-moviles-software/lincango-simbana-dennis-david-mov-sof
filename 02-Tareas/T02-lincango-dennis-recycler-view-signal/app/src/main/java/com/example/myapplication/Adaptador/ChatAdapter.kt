package com.example.myapplication.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Entity.Chat
import com.example.myapplication.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class ChatAdapter(private val chatList: List<Chat>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textUserName: TextView = itemView.findViewById(R.id.textUserName)
        val textMessage: TextView = itemView.findViewById(R.id.textMessage)
        val imageChat: ImageView = itemView.findViewById(R.id.imageChat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val currentChat = chatList[position]
        holder.textUserName.text = currentChat.userName
        holder.textMessage.text = currentChat.message

        Glide.with(holder.itemView)
            .load(currentChat.profileURL) // La URL de la imagen del mensaje de chat
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Opcional: Cachear la imagen para futuras cargas
            .into(holder.imageChat)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}