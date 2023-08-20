@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.adapter

import com.example.myapplication.entity.Menu

interface AdapterListenerMenu {
    fun onEditItemClick(menu: Menu)
    fun onDeleteItemClick(menu: Menu)
}