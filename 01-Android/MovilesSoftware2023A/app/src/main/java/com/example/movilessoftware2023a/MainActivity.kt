package com.example.movilessoftware2023a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Se crea una variable para el botón ciclo de vida
        val botonACicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )

        //Se añade un listener al botón ciclo de vida
        botonACicloVida.setOnClickListener {
            irActividad(AACicloVida::class.java)
        }

        val botonIrListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )

        botonIrListView.setOnClickListener {
            irActividad(BListView::class.java)
        }

    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}