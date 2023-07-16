@file:Suppress("SpellCheckingInspection")

package com.example.myapplication.vista

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.almacenamiento.BaseDatosMemoria
import com.example.myapplication.modelo.dao.ComidaDAO

class UpdateComidaView : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_update_comida)

        val intent = intent
        val comidaId = intent.getIntExtra(
            "comidaId",
            -1
        ) // -1 es un valor predeterminado si no se encuentra el extra


        val comidaDAO = ComidaDAO()
        val comida = comidaDAO.getComidaById(comidaId)
        val nombreEditText = findViewById<EditText>(R.id.txtNombreComida)
        val tipoEditText = findViewById<EditText>(R.id.txtTipoComida)
        val descripcionEditText = findViewById<EditText>(R.id.txtDescripcionComida)
        val caloriasEditText = findViewById<EditText>(R.id.txtCaloriasComida)
        val ingredientesEditText = findViewById<EditText>(R.id.txtIngredientesComida)


        if (comida != null) {
            val nombre = comida.nombre
            val tipo = comida.tipo
            val descripcion = comida.descripcion
            val calorias = comida.calorias
            val ingredientes = comida.ingredientes

            // Autocompletar los campos con los datos del menú
            nombreEditText.setText(nombre)
            tipoEditText.setText(tipo)
            descripcionEditText.setText(descripcion)
            caloriasEditText.setText(calorias.toString())
            ingredientesEditText.setText(ingredientes.toString())
        }

        val buttonInsertComida =
            findViewById<Button>(R.id.btnAceptarComida) // Obtener referencia al botón

        buttonInsertComida.setOnClickListener {
            // Resto de la lógica para actualizar el menú
            comida?.let {
                it.nombre = nombreEditText.text.toString()
                it.tipo = tipoEditText.text.toString()
                it.descripcion = descripcionEditText.text.toString()
                it.calorias = caloriasEditText.text.toString().toDouble()
                it.ingredientes = ingredientesEditText.text.toString().split(",")
                //it.refrenciaIdMenu = it.refrenciaIdMenu

                comidaDAO.updateComida(it)
                updateTextView()
            }
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateTextView() {
        val stringBuilder = StringBuilder()
        for (comida in BaseDatosMemoria.listaComidas) {
            stringBuilder.append(
              //  "Id: ${comida.id}\n",
                "Nombre: ${comida.nombre}\n",
                "Tipo: ${comida.tipo}\n",
                "Descripción: ${comida.descripcion}\n",
                "Calorías: ${comida.calorias}\n",
                "Ingredientes: ${comida.ingredientes}\n\n"
            )
        }

    }

}