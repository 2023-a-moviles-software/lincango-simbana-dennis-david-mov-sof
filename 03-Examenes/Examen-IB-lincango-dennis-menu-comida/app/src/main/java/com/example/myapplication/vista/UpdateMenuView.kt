@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.vista

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.almacenamiento.BaseDatosMemoria
import com.example.myapplication.modelo.dao.MenuDAO
import java.time.LocalDateTime


class UpdateMenuView : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_update_menu)

        val intent = intent
        val menuId = intent.getIntExtra(
            "menuId",
            -1
        ) // -1 es un valor predeterminado si no se encuentra el extra

        val menuDAO = MenuDAO()
        val menu = menuDAO.getMenuById(menuId)
        val nombreEditText = findViewById<EditText>(R.id.txteditTextNameMenu)
        val disponibilidadEditText = findViewById<Switch>(R.id.swDisponiblilidad)
        val precioEditText = findViewById<EditText>(R.id.txtPrecio)
        val calificacionEditText = findViewById<EditText>(R.id.txtCalificacion)

        if (menu != null) {
            val nombre = menu.nombre
            val disponible = menu.disponible
            val precio = menu.precio
            val calificacion = menu.calificacion

            // Autocompletar los campos con los datos del menú
            nombreEditText.setText(nombre)
            disponibilidadEditText.isChecked = disponible
            precioEditText.setText(precio.toString())
            calificacionEditText.setText(calificacion.toString())
        }

        val buttonInsertMenu = findViewById<Button>(R.id.btnInsertMenu) // Obtener referencia al botón

        buttonInsertMenu.setOnClickListener {
            // Resto de la lógica para actualizar el menú
            menu?.let {
                it.nombre = nombreEditText.text.toString()
                it.disponible = disponibilidadEditText.text.toString().toBoolean()
                it.precio = precioEditText.text.toString().toDouble()
                it.calificacion = calificacionEditText.text.toString().toInt()
                it.fechaAdicion = LocalDateTime.now()

                menuDAO.updateMenu(it)
                updateTextView()
            }
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateTextView() {
        val stringBuilder = StringBuilder()
        for (menu in BaseDatosMemoria.listaMenus) {
            stringBuilder.append(
               // "Id: ${menu.id}\n",
                "Nombre: ${menu.nombre}\n",
                "Disponible: ${menu.disponible}\n",
                "Precio: ${menu.precio}\n",
                "Calificacion: ${menu.calificacion}\n",
                "Fecha Adicion: ${menu.fechaAdicion}\n\n"
            )
        }

    }

    /*private fun clearInputs() {
        findViewById<EditText>(R.id.editTextNameMenu).text.clear()
        findViewById<EditText>(R.id.editTextDisponibilidad).text.clear()
        findViewById<EditText>(R.id.editTextPrecio).text.clear()
        findViewById<EditText>(R.id.editTextCalificacion).text.clear()
    }*/

}
