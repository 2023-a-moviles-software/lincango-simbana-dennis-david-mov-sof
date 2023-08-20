@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.adapter

import com.example.myapplication.entity.Comida

interface AdapterListenerComida {
    fun onEditItemClick(comida: Comida)
    fun onDeleteItemClick(comida: Comida)
}