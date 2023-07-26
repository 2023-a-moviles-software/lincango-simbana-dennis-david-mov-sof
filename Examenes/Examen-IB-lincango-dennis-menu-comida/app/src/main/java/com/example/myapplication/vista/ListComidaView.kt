@file:Suppress("SpellCheckingInspection")

package com.example.myapplication.vista

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.almacenamiento.BaseDatosMemoria
import com.example.myapplication.modelo.dao.ComidaDAO

class ListComidaView : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var listaComidas = BaseDatosMemoria.listaComidas
    private var idItemSeleccionado = 0
    private var idItemEliminar = 0
    private var idMenu: Int = -1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_comida)

        idMenu = intent.getIntExtra("menuId", -1)

        configurarListView()
        val botonNuevo = findViewById<Button>(R.id.btn_agregar_comida)
        botonNuevo.setOnClickListener {
            irActividad(CreateComidaView::class.java)
        }

    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        intent.putExtra("menuId", idMenu)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun configurarListView() {
        val listView = findViewById<ListView>(R.id.lv_comida)

        listaComidas = ComidaDAO().getComidaByMenuId(idMenu)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaComidas
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun actualizarListView() {
        val listView = findViewById<ListView>(R.id.lv_comida)

        listaComidas = ComidaDAO().getComidaByMenuId(idMenu)


        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaComidas
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_editar_comida -> {
                val comidaId = idItemSeleccionado // Tu ID específico
                val intent = Intent(this, UpdateComidaView::class.java)
                intent.putExtra("comidaId", comidaId)
                startActivity(intent)
                return true
            }

            R.id.item_eliminar_comida -> {
                val comida = listaComidas[idItemEliminar]
                val comidaDAO = ComidaDAO()
                comidaDAO.deleteComida(comida)
                actualizarListView()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // llenar las opciones del menu (eliminar, editar)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_comida, menu)

        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val comidaId = listaComidas[info.position].id
        val comidaIdDelete = info.id.toInt()
        idItemSeleccionado = comidaId
        idItemEliminar = comidaIdDelete
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRestart() {
        super.onRestart()
        actualizarListView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        actualizarListView()
    }

}