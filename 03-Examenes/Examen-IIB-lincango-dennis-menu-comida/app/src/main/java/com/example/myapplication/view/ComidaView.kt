@file:Suppress("SpellCheckingInspection")

package com.example.myapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.databinding.ActivityComidaBinding
import com.example.myapplication.personalization.objects.Objects
import com.example.myapplication.R
import com.example.myapplication.personalization.Comidas
import com.example.myapplication.personalization.objects.AdapterListenerComida
import com.example.myapplication.entity.entity.Comida
import kotlinx.coroutines.launch

class ComidaView : AppCompatActivity(), AdapterListenerComida {

    lateinit var update: ActivityComidaBinding

    var listaComidas: MutableList<Comida> = mutableListOf()

    lateinit var adaptador: Comidas

    lateinit var db: Objects

    lateinit var comida: Comida

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        update = ActivityComidaBinding.inflate(layoutInflater)
        setContentView(update.root)

        update.rvComidas.layoutManager = LinearLayoutManager(this)

        db = Room.databaseBuilder(this, Objects::class.java, "dbPruebas").build()

        obtenerComidas(db)

        setTitle(R.string.title_comida)

        update.btnAddUpdateComida.setOnClickListener {

            if (update.etNombreComida.text.isNullOrEmpty() ||
                update.etTipoComida.text.isNullOrEmpty() ||
                update.etDescripcionComida.text.isNullOrEmpty() ||
                update.etCaloriasComida.text.isNullOrEmpty()
            ) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (update.btnAddUpdateComida.text.equals("agregar")) {
                comida = Comida(
                    listaComidas.size + 1,
                    update.etNombreComida.text.toString().trim(),
                    update.etTipoComida.text.toString().trim(),
                    update.etDescripcionComida.text.toString().trim(),
                    update.etCaloriasComida.text.toString().trim().toDouble(),
                    ontenerMenuId()
                )
                agregarComida(db, comida)
            } else if (update.btnAddUpdateComida.text.equals("actualizar")) {
                comida.nombre = update.etNombreComida.text.toString().trim()
                comida.tipo = update.etTipoComida.text.toString().trim()
                comida.descripcion = update.etDescripcionComida.text.toString().trim()
                comida.calorias = update.etCaloriasComida.text.toString().trim().toDouble()
                actualizarComida(db, comida)
            }
        }

    }

    fun ontenerMenuId(): Int {

        return intent.getIntExtra("menuId", -1)
    }

    fun obtenerComidas(room: Objects) {
        lifecycleScope.launch {
         // listaComidas = room.comidaDAO().getComidas()
            listaComidas = room.comidaDAO().getComidasByMenuId(ontenerMenuId()).toMutableList()
            adaptador = Comidas(listaComidas, this@ComidaView)
            update.rvComidas.adapter = adaptador
        }
    }

    fun agregarComida(room: Objects, comida: Comida) {
        lifecycleScope.launch {
            room.comidaDAO().createComida(comida)
            obtenerComidas(room)
            limpiarCampos()
        }
    }

    fun actualizarComida(room: Objects, comida: Comida) {
        lifecycleScope.launch {
            room.comidaDAO().updateComida(comida)
            obtenerComidas(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        update.etNombreComida.setText("")
        update.etTipoComida.setText("")
        update.etDescripcionComida.setText("")
        update.etCaloriasComida.setText("")
        if (update.btnAddUpdateComida.text.equals("actualizar")) {
            update.btnAddUpdateComida.setText("agregar")
        }
    }

    override fun onEditItemClick(comida: Comida) {
        update.btnAddUpdateComida.setText("actualizar")
        this.comida = comida
        update.etNombreComida.setText(this.comida.nombre)
        update.etTipoComida.setText(this.comida.tipo)
        update.etDescripcionComida.setText(this.comida.descripcion)
        update.etCaloriasComida.setText(this.comida.calorias.toString())
    }

    override fun onDeleteItemClick(comida: Comida) {
        lifecycleScope.launch {
            db.comidaDAO().deleteComida(comida.id)
            adaptador.notifyDataSetChanged()
            obtenerComidas(db)
            limpiarCampos()
        }
    }

}