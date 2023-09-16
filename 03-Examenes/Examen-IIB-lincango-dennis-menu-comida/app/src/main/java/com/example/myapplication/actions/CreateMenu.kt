package com.example.myapplication.actions

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.entity.entity.Comida
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateMenu : AppCompatActivity() {

    lateinit var comida: Comida

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val botonCrear = findViewById<Button>(R.id.btnAddUpdate)

        botonCrear.setOnClickListener {

            val nombre = findViewById<EditText>(R.id.etNombre)
            val disponible = findViewById<Switch>(R.id.swDisponiblilidad).isChecked
            val precio = findViewById<EditText>(R.id.etPrecio).text.toString()
            val calificacion = findViewById<EditText>(R.id.etCalificacion).text.toString()


            crearComida(
                nombre.toString(),
                disponible,
                precio.toDouble(),
                calificacion.toDouble(),
            )
        }
    }

    private fun crearComida(
        nombre: String?,
        disponible: Boolean?,
        precio: Double?,
        calificacion: Double?,

    ) {
        val db = Firebase.firestore
        val referenciaComidas = db
            .collection("menus")

        referenciaComidas.get().addOnSuccessListener { querySnapshot ->
            val nuevoId = querySnapshot.size() + 1

            val datosMenu = hashMapOf(
                "id" to nuevoId,
                "nombre" to nombre,
                "disponible" to disponible,
                "precio" to precio,
                "calificacion" to calificacion
            )
            if (nombre != null) {
                referenciaComidas.document(nuevoId.toString()).set(datosMenu)
            }
            finish()
        }
    }
}
