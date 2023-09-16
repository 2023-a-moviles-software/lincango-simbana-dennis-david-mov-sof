package com.example.myapplication.actions

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.entity.entity.Comida
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateComida : AppCompatActivity() {

    lateinit var comida: Comida

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val botonCrear = findViewById<Button>(R.id.btnAddUpdate)

        botonCrear.setOnClickListener {

            val nombre = findViewById<EditText>(R.id.etNombre).text.toString()
            val tipo = findViewById<EditText>(R.id.etTipoComida).text.toString()
            val descripcion = findViewById<EditText>(R.id.etDescripcionComida).text.toString()
            val calorias = findViewById<EditText>(R.id.etCaloriasComida).text.toString()

            crearComida(
                nombre,
                tipo,
                descripcion,
                calorias.toDouble()
            )
        }
    }

    private fun crearComida(
        nombre: String?,
        tipo: String?,
        descripcion: String?,
        calorias: Double?
    ) {
        val db = Firebase.firestore
        val referenciaComidas = db
            .collection("comidas")

        referenciaComidas.get().addOnSuccessListener { querySnapshot ->
            val nuevoId = querySnapshot.size() + 1

            val datosComida = hashMapOf(
                "id" to nuevoId,
                "nombre" to nombre,
                "tipo" to tipo,
                "descripcion" to descripcion,
                "calorias" to calorias
            )
            if (nombre != null) {
                referenciaComidas.document(nuevoId.toString()).set(datosComida)
            }
            finish()
        }
    }
}
