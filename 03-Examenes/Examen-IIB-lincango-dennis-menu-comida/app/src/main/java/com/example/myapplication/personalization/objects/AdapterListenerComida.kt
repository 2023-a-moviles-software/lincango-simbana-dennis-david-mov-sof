@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.personalization.objects

import com.example.myapplication.entity.entity.Comida

interface AdapterListenerComida {
    fun onEditItemClick(comida: Comida)
    fun onDeleteItemClick(comida: Comida)
}