@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.view

import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.personalization.objects.Objects
import com.example.myapplication.R
import com.example.myapplication.personalization.objects.AdapterListenerMenu
import com.example.myapplication.personalization.Menus
import com.example.myapplication.databinding.ActivityMenuBinding
import com.example.myapplication.entity.entity.Menu
import kotlinx.coroutines.launch
import java.util.Calendar

class MenuView : AppCompatActivity(), AdapterListenerMenu {

    lateinit var update: ActivityMenuBinding

    var listaMenus: MutableList<Menu> = mutableListOf()

    lateinit var adaptador: Menus

    lateinit var db: Objects

    lateinit var menu: Menu


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        update = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(update.root)

        update.rvMenus.layoutManager = LinearLayoutManager(this)

        db = Room.databaseBuilder(this, Objects::class.java, "dbPruebas").build()

        obtenerMenus(db)

        setTitle(R.string.title_menu)

        update.btnAddUpdate.setOnClickListener {

            if (update.etNombre.text.isNullOrEmpty() ||
                update.swDisponiblilidad.text.isNullOrEmpty() ||
                update.etPrecio.text.isNullOrEmpty() ||
                update.etCalificacion.text.isNullOrEmpty()
            ) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (update.btnAddUpdate.text.equals("agregar")) {
                menu = Menu(
                    listaMenus.size + 1,
                    update.etNombre.text.toString().trim(),
                    update.swDisponiblilidad.isChecked,
                    update.etPrecio.text.toString().trim().toDouble(),
                    update.etCalificacion.text.toString().trim().toDouble(),
                    obtenerFechaSeleccionada(update.etFechaAdicion)

                )
                agregarMenu(db, menu)
            } else if (update.btnAddUpdate.text.equals("actualizar")) {
                menu.nombre = update.etNombre.text.toString().trim()
                menu.disponible = update.swDisponiblilidad.isChecked // Utiliza isChecked para obtener el estado del Switch
                menu.precio = update.etPrecio.text.toString().trim().toDouble()
                menu.calificacion = update.etCalificacion.text.toString().trim().toDouble()
                menu.fechaAdicion = obtenerFechaSeleccionada(update.etFechaAdicion)

                actualizarMenu(db, menu)
            }
        }

    }

    private fun obtenerFechaSeleccionada(datePicker: DatePicker): String {
        val year = datePicker.year
        val month = datePicker.month + 1 // Los meses en DatePicker van de 0 a 11
        val day = datePicker.dayOfMonth

        return String.format("%04d-%02d-%02d", year, month, day)
    }

    fun obtenerMenus(room: Objects) {
        lifecycleScope.launch {
            listaMenus = room.menuDAO().getMenus()
            adaptador = Menus(listaMenus, this@MenuView)
            update.rvMenus.adapter = adaptador
        }
    }

    fun agregarMenu(room: Objects, menu: Menu) {
        lifecycleScope.launch {
            room.menuDAO().crearMenu(menu)
            obtenerMenus(room)
            limpiarCampos()
        }
    }

    fun actualizarMenu(room: Objects, menu: Menu) {
        lifecycleScope.launch {
            room.menuDAO().updateMenu(menu)
            obtenerMenus(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        update.etNombre.setText("")
        update.swDisponiblilidad.isChecked = false
        update.etPrecio.setText("")
        update.etCalificacion.setText("")

        val today = Calendar.getInstance()
        update.etFechaAdicion.updateDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH))

        if (update.btnAddUpdate.text.equals("actualizar")) {
            update.btnAddUpdate.setText("agregar")
            //  binding.etNombre.isEnabled = true
        }

    }

    override fun onEditItemClick(menu: Menu) {
        update.btnAddUpdate.setText("actualizar")
        //  binding.etNombre.isEnabled = false


        this.menu = menu
        update.etNombre.setText(this.menu.nombre)
        update.swDisponiblilidad.isChecked = this.menu.disponible
        update.etPrecio.setText(this.menu.precio.toString())
        update.etCalificacion.setText(this.menu.calificacion.toString())


        val fechaAdicion = this.menu.fechaAdicion // Supongamos que es una cadena en formato "YYYY-MM-DD"
        val fechaParts = fechaAdicion.split("-")
        if (fechaParts.size == 3) {
            val year = fechaParts[0].toInt()
            val month = fechaParts[1].toInt() - 1 // Restamos 1 para convertir de 1-12 a 0-11
            val day = fechaParts[2].toInt()
            update.etFechaAdicion.updateDate(year, month, day)
        }

    }

    override fun onDeleteItemClick(menu: Menu) {
        lifecycleScope.launch {
            db.menuDAO().deleteMenu(menu)
            adaptador.notifyDataSetChanged()
            obtenerMenus(db)
        }
    }

}