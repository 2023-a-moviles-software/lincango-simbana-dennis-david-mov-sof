@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.personalization.objects

import com.example.myapplication.entity.entity.Menu

interface AdapterListenerMenu {
    fun onEditItemClick(menu: Menu)
    fun onDeleteItemClick(menu: Menu)
}