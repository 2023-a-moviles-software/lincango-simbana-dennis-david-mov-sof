@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.vista

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.almacenamiento.BaseDatosMemoria
import com.example.myapplication.modelo.dao.MenuDAO

class ListMenuView : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    val listaMenus = BaseDatosMemoria.listaMenus
    private var idItemSeleccionado = 0
    private var idItemEliminar = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_menu)
        configurarListView()
        val botonNuevo = findViewById<Button>(R.id.btnNuevoMenu)
        botonNuevo.setOnClickListener {
            irActividad(CreateMenuView::class.java)
        }

    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun configurarListView() {
        val listView = findViewById<ListView>(R.id.listViewMenu)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaMenus
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun actualizarListView() {
        val listView = findViewById<ListView>(R.id.listViewMenu)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaMenus
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_edit_menu -> {
                val menuId = idItemSeleccionado // Tu ID específico
                val intent = Intent(this, UpdateMenuView::class.java)
                intent.putExtra("menuId", menuId)
                startActivity(intent)
                return true
            }

            R.id.item_eliminar_menu -> {
                val menu = listaMenus[idItemEliminar]
                val menuDAO = MenuDAO()
                menuDAO.deleteMenu(menu)
                actualizarListView()
                return true
            }

            R.id.item_ver_comidas -> {
                val menuId = idItemSeleccionado // Tu ID específico
                val intent = Intent(this, ListComidaView::class.java)
                intent.putExtra("menuId", menuId)
                startActivity(intent)
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
        // llenar las opciones del menu (editar, eliminar, ver comidas)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val menuId = listaMenus[info.position].id
        val menuIdDelete = info.id.toInt()
        // Guardar el ID en una variable para usarlo en otros métodos
        idItemSeleccionado = menuId
        idItemEliminar = menuIdDelete
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