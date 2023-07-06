package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movilescomputacion2023a.BBaseDatosMemoria

class FRecyclerView : AppCompatActivity() {
    var totalLikes = 0
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frecycler_view)
        // Definir lista
        inicializarRecyclerView()
    }

    fun inicializarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(
            R.id.rv_entrenadores
        )

        val adaptador = FReciclerViewAdaptadorNombreCedula(
            this,
            arreglo,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarTotalLikes() {
        totalLikes = totalLikes + 1
        val totalLikesTextView = findViewById<TextView>(
            R.id.tv_total_likes
        )
        totalLikesTextView.text = totalLikes.toString()
    }


}