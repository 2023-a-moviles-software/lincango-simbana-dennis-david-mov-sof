@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.personalization.objects

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.personalization.dao.ComidaDAO
import com.example.myapplication.personalization.dao.MenuDAO
import com.example.myapplication.entity.entity.Comida
import com.example.myapplication.entity.entity.Menu

@Database(
    entities = [Menu::class,
                Comida::class],
    version = 1
)
abstract class Objects: RoomDatabase() {
    abstract fun menuDAO(): MenuDAO
    abstract fun comidaDAO(): ComidaDAO
}