@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.entity.Menu
import com.example.myapplication.view.ComidaView

class AdapterMenus(
    val listaMenus: MutableList<Menu>,
    val listener: AdapterListenerMenu
): RecyclerView.Adapter<AdapterMenus.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_menu, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu = listaMenus[position]

        holder.tvNombre.text = menu.nombre
        holder.tvDisponible.text = menu.disponible.toString()
        holder.tvPrecio.text = menu.precio.toString()
        holder.tvCalificacion.text = menu.calificacion.toString()
        holder.tvFechaAdicion.text = menu.fechaAdicion


        holder.cvMenu.setOnClickListener {
            listener.onEditItemClick(menu)
        }

        holder.btnBorrar.setOnClickListener {
            listener.onDeleteItemClick(menu)
        }


        holder.btnVerComidas.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ComidaView::class.java)
            intent.putExtra("menuId", menu.id)
            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int {
        return listaMenus.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val cvMenu = itemView.findViewById<CardView>(R.id.cvMenu)
        val tvNombre = itemView.findViewById<TextView>(R.id.tvnombre)
        val tvDisponible = itemView.findViewById<TextView>(R.id.tvdisponible)
        val tvPrecio = itemView.findViewById<TextView>(R.id.tvprecio)
        val tvCalificacion = itemView.findViewById<TextView>(R.id.tvcalificacion)
        val tvFechaAdicion = itemView.findViewById<TextView>(R.id.tvfechaadicion)

        val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar)
        val btnVerComidas = itemView.findViewById<Button>(R.id.btnVerComidas)
    }

}