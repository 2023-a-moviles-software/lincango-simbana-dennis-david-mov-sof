package com.example.myapplication.modelo.dao

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.almacenamiento.BaseDatosMemoria
import com.example.myapplication.modelo.entity.Menu

class MenuDAO {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        private var menuIdCounter = MenuDAO().getAllMenus().size + 1
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createMenu(menu: Menu) {
        menu.id = menuIdCounter
        BaseDatosMemoria.listaMenus.add(menu)
        menuIdCounter++
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateMenu(menu: Menu) {
        val index = BaseDatosMemoria.listaMenus.indexOfFirst {
            it.id == menu.id
        }
        BaseDatosMemoria.listaMenus[index] = menu
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun deleteMenu(menu: Menu) {
        BaseDatosMemoria.listaMenus.remove(menu)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllMenus(): MutableList<Menu> {
        return BaseDatosMemoria.listaMenus
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMenuById(id: Int): Menu? {
        return BaseDatosMemoria.listaMenus.find { it.id == id }
    }

}