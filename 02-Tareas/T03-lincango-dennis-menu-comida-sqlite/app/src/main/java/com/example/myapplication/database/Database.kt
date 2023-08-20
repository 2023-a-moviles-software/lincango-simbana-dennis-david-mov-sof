@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.dao.ComidaDAO
import com.example.myapplication.dao.MenuDAO
import com.example.myapplication.entity.Comida
import com.example.myapplication.entity.Menu

@Database(
    entities = [Menu::class,
                Comida::class],
    version = 1
)
abstract class Database: RoomDatabase() {
    abstract fun menuDAO(): MenuDAO
    abstract fun comidaDAO(): ComidaDAO
}