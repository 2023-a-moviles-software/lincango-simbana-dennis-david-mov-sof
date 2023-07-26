package com.example.myapplication.modelo.dao

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.almacenamiento.BaseDatosMemoria
import com.example.myapplication.modelo.entity.Comida

class ComidaDAO {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        private var comidaIdCounter = ComidaDAO().getAllComidas().size + 1
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createComida(comida: Comida) {
        comida.id = comidaIdCounter
        BaseDatosMemoria.listaComidas.add(comida)
        comidaIdCounter++
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateComida(comida: Comida) {
        val index = BaseDatosMemoria.listaComidas.indexOfFirst {
            it.id == comida.id
        }
        BaseDatosMemoria.listaComidas[index] = comida
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun deleteComida(comida: Comida) {
        BaseDatosMemoria.listaComidas.remove(comida)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllComidas(): MutableList<Comida> {
        return BaseDatosMemoria.listaComidas
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getComidaById(id: Int): Comida? {
        return BaseDatosMemoria.listaComidas.find { it.id == id }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getComidaByMenuId(id: Int): MutableList<Comida> {
        return BaseDatosMemoria.listaComidas.filter { it.refrenciaIdMenu == id }.toMutableList()
    }

}