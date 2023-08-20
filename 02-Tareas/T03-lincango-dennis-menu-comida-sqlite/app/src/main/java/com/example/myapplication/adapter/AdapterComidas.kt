@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.entity.Comida

class AdapterComidas(
    val listaComidas: MutableList<Comida>,
    val listener: AdapterListenerComida
): RecyclerView.Adapter<AdapterComidas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_comida, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comida = listaComidas[position]

        holder.tvNombre.text = comida.nombre
        holder.tvTipo.text = comida.tipo
        holder.tvDescripcion.text = comida.descripcion
        holder.tvCalorias.text = comida.calorias.toString()

        holder.cvComida.setOnClickListener {
            listener.onEditItemClick(comida)
        }

        holder.btnBorrarComida.setOnClickListener {
            listener.onDeleteItemClick(comida)
        }

    }

    override fun getItemCount(): Int {
        return listaComidas.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val cvComida = itemView.findViewById<CardView>(R.id.cvComida)

        val tvNombre = itemView.findViewById<TextView>(R.id.tvnombrecomida)
        val tvTipo = itemView.findViewById<TextView>(R.id.tvtipocomida)
        val tvDescripcion = itemView.findViewById<TextView>(R.id.tvdescripcioncomida)
        val tvCalorias = itemView.findViewById<TextView>(R.id.tvcaloriascomida)

        val btnBorrarComida = itemView.findViewById<Button>(R.id.btnBorrarComida)

    }

}
