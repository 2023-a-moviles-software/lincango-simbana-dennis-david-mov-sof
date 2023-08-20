@file:Suppress("SpellCheckingInspection")

package com.example.myapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.databinding.ActivityComidaBinding
import com.example.myapplication.database.Database
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterComidas
import com.example.myapplication.adapter.AdapterListenerComida
import com.example.myapplication.entity.Comida
import kotlinx.coroutines.launch

class ComidaView : AppCompatActivity(), AdapterListenerComida {

    lateinit var binding: ActivityComidaBinding

    var listaComidas: MutableList<Comida> = mutableListOf()

    lateinit var adaptador: AdapterComidas

    lateinit var room: Database

    lateinit var comida: Comida

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvComidas.layoutManager = LinearLayoutManager(this)

        room = Room.databaseBuilder(this, Database::class.java, "dbPruebas").build()

        obtenerComidas(room)

        setTitle(R.string.title_comida)

        binding.btnAddUpdateComida.setOnClickListener {

            if (binding.etNombreComida.text.isNullOrEmpty() ||
                binding.etTipoComida.text.isNullOrEmpty() ||
                binding.etDescripcionComida.text.isNullOrEmpty() ||
                binding.etCaloriasComida.text.isNullOrEmpty()
            ) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.btnAddUpdateComida.text.equals("agregar")) {
                comida = Comida(
                    listaComidas.size + 1,
                    binding.etNombreComida.text.toString().trim(),
                    binding.etTipoComida.text.toString().trim(),
                    binding.etDescripcionComida.text.toString().trim(),
                    binding.etCaloriasComida.text.toString().trim().toDouble(),
                    ontenerMenuId()
                )
                agregarComida(room, comida)
            } else if (binding.btnAddUpdateComida.text.equals("actualizar")) {
                comida.nombre = binding.etNombreComida.text.toString().trim()
                comida.tipo = binding.etTipoComida.text.toString().trim()
                comida.descripcion = binding.etDescripcionComida.text.toString().trim()
                comida.calorias = binding.etCaloriasComida.text.toString().trim().toDouble()
                actualizarComida(room, comida)
            }
        }

    }

    fun ontenerMenuId(): Int {

        return intent.getIntExtra("menuId", -1)
    }

    fun obtenerComidas(room: Database) {
        lifecycleScope.launch {
         // listaComidas = room.comidaDAO().getComidas()
            listaComidas = room.comidaDAO().getComidasByMenuId(ontenerMenuId()).toMutableList()
            adaptador = AdapterComidas(listaComidas, this@ComidaView)
            binding.rvComidas.adapter = adaptador
        }
    }

    fun agregarComida(room: Database, comida: Comida) {
        lifecycleScope.launch {
            room.comidaDAO().createComida(comida)
            obtenerComidas(room)
            limpiarCampos()
        }
    }

    fun actualizarComida(room: Database, comida: Comida) {
        lifecycleScope.launch {
            room.comidaDAO().updateComida(comida)
            obtenerComidas(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        binding.etNombreComida.setText("")
        binding.etTipoComida.setText("")
        binding.etDescripcionComida.setText("")
        binding.etCaloriasComida.setText("")
        if (binding.btnAddUpdateComida.text.equals("actualizar")) {
            binding.btnAddUpdateComida.setText("agregar")
        }
    }

    override fun onEditItemClick(comida: Comida) {
        binding.btnAddUpdateComida.setText("actualizar")
        this.comida = comida
        binding.etNombreComida.setText(this.comida.nombre)
        binding.etTipoComida.setText(this.comida.tipo)
        binding.etDescripcionComida.setText(this.comida.descripcion)
        binding.etCaloriasComida.setText(this.comida.calorias.toString())
    }

    override fun onDeleteItemClick(comida: Comida) {
        lifecycleScope.launch {
            room.comidaDAO().deleteComida(comida.id)
            adaptador.notifyDataSetChanged()
            obtenerComidas(room)
            limpiarCampos()
        }
    }


}