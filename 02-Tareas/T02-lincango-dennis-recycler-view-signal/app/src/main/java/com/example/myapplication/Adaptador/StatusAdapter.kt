package com.example.myapplication.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Entity.Status
import com.example.myapplication.R

class StatusAdapter(private val statusList: List<Status>):
    RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

        class StatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val profileImage: ImageView = itemView.findViewById(R.id.imageProfile)
            val textStory: TextView = itemView.findViewById(R.id.textStory)
            val textTime: TextView = itemView.findViewById(R.id.textTime)
            val statusImage: ImageView = itemView.findViewById(R.id.imageStatus)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_status, parent, false)
            return StatusViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
            val statusCurrent = statusList[position]
            holder.textStory.text = statusCurrent.user
            holder.textTime.text = statusCurrent.timePublication

            Glide.with(holder.itemView)
                .load(statusCurrent.profileImageURL) // La URL de la imagen del perfil
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Opcional: Cachear la imagen para futuras cargas
                .into(holder.profileImage)

            Glide.with(holder.itemView)
                .load(statusCurrent.imageStatusURL) // La URL de la imagen del estado
                .apply(RequestOptions.bitmapTransform(RoundedCorners(16))) // Opcional: Redondear las esquinas de la imagen
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Opcional: Cachear la imagen para futuras cargas
                .into(holder.statusImage)
        }

        override fun getItemCount(): Int {
            return statusList.size
        }
    }