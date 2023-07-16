@file:Suppress("SpellCheckingInspection")

package com.example.myapplication.vista

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.modelo.dao.MenuDAO
import com.example.myapplication.modelo.entity.Menu
import java.time.LocalDateTime

class CreateMenuView : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_update_menu)

        val buttonInsertMenu =
            findViewById<Button>(R.id.btnInsertMenu) // Obtener referencia al botón

        buttonInsertMenu.setOnClickListener {
            val id = MenuDAO().getAllMenus().size + 1
            val name = findViewById<EditText>(R.id.txteditTextNameMenu).text.toString()
            val disponible = findViewById<Switch>(R.id.swDisponiblilidad).isChecked
            val precio = findViewById<EditText>(R.id.txtPrecio).text.toString().toDouble()
            val calificacion = findViewById<EditText>(R.id.txtCalificacion).text.toString().toInt()
            val fechaAdicion = LocalDateTime.now()

            if (name.isNotEmpty()) {
                val menu = Menu(id, name, disponible, precio, calificacion, fechaAdicion)
                val menuDAO = MenuDAO()
                menuDAO.createMenu(menu)
                updateTextView()
            //    clearInputs()
            }
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateTextView() {
        val stringBuilder = StringBuilder()
        for (menu in MenuDAO().getAllMenus()) {
            stringBuilder.append(
                //  "Id: ${menu.id}\n",
                "Nombre: ${menu.nombre}\n",
                "Disponible: ${menu.disponible}\n",
                "Precio: ${menu.precio}\n",
                "Calificacion: ${menu.calificacion}\n",
                "Fecha Adicion: ${menu.fechaAdicion}\n\n"
            )
        }
    }

    /*
    private fun clearInputs() {
        findViewById<EditText>(R.id.txteditTextNameMenu).text.clear()
        findViewById<Switch>(R.id.swDisponiblilidad).isChecked = false
        findViewById<EditText>(R.id.txtPrecio).text.clear()
        findViewById<EditText>(R.id.txtCalificacion).text.clear()
    }*/

}