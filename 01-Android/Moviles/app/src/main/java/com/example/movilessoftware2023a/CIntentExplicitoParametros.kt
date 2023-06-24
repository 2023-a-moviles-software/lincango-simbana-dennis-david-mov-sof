package com.example.movilessoftware2023a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
        val nombre: String? = intent.getStringExtra("nombre")
        val apellido: String? = intent.getStringExtra("apellido")
        val edad: Int = intent.getIntExtra("edad", 0)
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton.setOnClickListener { devolverRespuesta() }
    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros
            .putExtra("nombreModificado", "Vicente")
        intentDevolverParametros
            .putExtra("edadModificado", 33)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
















}