package com.example.myapplication.vista

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.modelo.dao.ComidaDAO
import com.example.myapplication.modelo.entity.Comida

class CreateComidaView : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_update_comida)

        val buttonInsert =
            findViewById<Button>(R.id.btnAceptarComida) // Obtener referencia al botón

        buttonInsert.setOnClickListener {
            val id = ComidaDAO().getAllComidas().size + 1
            val name = findViewById<EditText>(R.id.txtNombreComida).text.toString()
            val type = findViewById<EditText>(R.id.txtTipoComida).text.toString()
            val description = findViewById<EditText>(R.id.txtDescripcionComida).text.toString()
            val calories = findViewById<EditText>(R.id.txtCaloriasComida).text.toString().toDouble()
            val ingredients =
                findViewById<EditText>(R.id.txtIngredientesComida).text.toString().split(",")
            val idMenu = intent.getIntExtra("menuId", -1)

            if (name.isNotEmpty()) {
                val comida = Comida(id, name, type, description, calories, ingredients, idMenu)
                val comidaDAO = ComidaDAO()
                comidaDAO.createComida(comida)
                updateTextView()
             //   clearInputs()
            }
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateTextView() {
        val stringBuilder = StringBuilder()
        for (comida in ComidaDAO().getAllComidas()) {
            stringBuilder.append(
                //  "Id: ${comida.id}\n",
                "Nombre: ${comida.nombre}\n",
                "Tipo: ${comida.tipo}\n",
                "Descripción: ${comida.descripcion}\n",
                "Calorias: ${comida.calorias}\n",
                "Ingredientes: ${comida.ingredientes}\n\n"
            )
        }

    }

    /*
    private fun clearInputs() {
        findViewById<EditText>(R.id.txtNombreComida).text.clear()
        findViewById<EditText>(R.id.txtTipoComida).text.clear()
        findViewById<EditText>(R.id.txtDescripcionComida).text.clear()
        findViewById<EditText>(R.id.txtCaloriasComida).text.clear()
        findViewById<EditText>(R.id.txtIngredientesComida).text.clear()
    }
*/
}