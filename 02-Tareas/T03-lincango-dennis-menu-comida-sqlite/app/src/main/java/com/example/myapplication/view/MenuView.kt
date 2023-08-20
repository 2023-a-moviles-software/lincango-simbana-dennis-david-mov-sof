@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.view

import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.database.Database
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterListenerMenu
import com.example.myapplication.adapter.AdapterMenus
import com.example.myapplication.databinding.ActivityMenuBinding
import com.example.myapplication.entity.Menu
import kotlinx.coroutines.launch
import java.util.Calendar

class MenuView : AppCompatActivity(), AdapterListenerMenu {

    lateinit var binding: ActivityMenuBinding

    var listaMenus: MutableList<Menu> = mutableListOf()

    lateinit var adaptador: AdapterMenus

    lateinit var room: Database

    lateinit var menu: Menu


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMenus.layoutManager = LinearLayoutManager(this)

        room = Room.databaseBuilder(this, Database::class.java, "dbPruebas").build()

        obtenerMenus(room)

        setTitle(R.string.title_menu)

        binding.btnAddUpdate.setOnClickListener {

            if (binding.etNombre.text.isNullOrEmpty() ||
                binding.swDisponiblilidad.text.isNullOrEmpty() ||
                binding.etPrecio.text.isNullOrEmpty() ||
                binding.etCalificacion.text.isNullOrEmpty()
            ) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.btnAddUpdate.text.equals("agregar")) {
                menu = Menu(
                    listaMenus.size + 1,
                    binding.etNombre.text.toString().trim(),
                    binding.swDisponiblilidad.isChecked,
                    binding.etPrecio.text.toString().trim().toDouble(),
                    binding.etCalificacion.text.toString().trim().toDouble(),
                    obtenerFechaSeleccionada(binding.etFechaAdicion)

                )
                agregarMenu(room, menu)
            } else if (binding.btnAddUpdate.text.equals("actualizar")) {
                menu.nombre = binding.etNombre.text.toString().trim()
                menu.disponible = binding.swDisponiblilidad.isChecked // Utiliza isChecked para obtener el estado del Switch
                menu.precio = binding.etPrecio.text.toString().trim().toDouble()
                menu.calificacion = binding.etCalificacion.text.toString().trim().toDouble()
                menu.fechaAdicion = obtenerFechaSeleccionada(binding.etFechaAdicion)

                actualizarMenu(room, menu)
            }
        }

    }

    private fun obtenerFechaSeleccionada(datePicker: DatePicker): String {
        val year = datePicker.year
        val month = datePicker.month + 1 // Los meses en DatePicker van de 0 a 11
        val day = datePicker.dayOfMonth

        return String.format("%04d-%02d-%02d", year, month, day)
    }

    fun obtenerMenus(room: Database) {
        lifecycleScope.launch {
            listaMenus = room.menuDAO().getMenus()
            adaptador = AdapterMenus(listaMenus, this@MenuView)
            binding.rvMenus.adapter = adaptador
        }
    }

    fun agregarMenu(room: Database, menu: Menu) {
        lifecycleScope.launch {
            room.menuDAO().crearMenu(menu)
            obtenerMenus(room)
            limpiarCampos()
        }
    }

    fun actualizarMenu(room: Database, menu: Menu) {
        lifecycleScope.launch {
            room.menuDAO().updateMenu(menu)
            obtenerMenus(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        binding.etNombre.setText("")
        binding.swDisponiblilidad.isChecked = false
        binding.etPrecio.setText("")
        binding.etCalificacion.setText("")

        val today = Calendar.getInstance()
        binding.etFechaAdicion.updateDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH))

        if (binding.btnAddUpdate.text.equals("actualizar")) {
            binding.btnAddUpdate.setText("agregar")
            //  binding.etNombre.isEnabled = true
        }

    }

    override fun onEditItemClick(menu: Menu) {
        binding.btnAddUpdate.setText("actualizar")
        //  binding.etNombre.isEnabled = false


        this.menu = menu
        binding.etNombre.setText(this.menu.nombre)
        binding.swDisponiblilidad.isChecked = this.menu.disponible
        binding.etPrecio.setText(this.menu.precio.toString())
        binding.etCalificacion.setText(this.menu.calificacion.toString())


        val fechaAdicion = this.menu.fechaAdicion // Supongamos que es una cadena en formato "YYYY-MM-DD"
        val fechaParts = fechaAdicion.split("-")
        if (fechaParts.size == 3) {
            val year = fechaParts[0].toInt()
            val month = fechaParts[1].toInt() - 1 // Restamos 1 para convertir de 1-12 a 0-11
            val day = fechaParts[2].toInt()
            binding.etFechaAdicion.updateDate(year, month, day)
        }

    }

    override fun onDeleteItemClick(menu: Menu) {
        lifecycleScope.launch {
            room.menuDAO().deleteMenu(menu)
            adaptador.notifyDataSetChanged()
            obtenerMenus(room)
        }
    }

}