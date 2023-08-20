@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.entity.Menu

@Dao
interface MenuDAO {

    @Query("SELECT * FROM menus")
    suspend fun getMenus(): MutableList<Menu>

    @Insert
    suspend fun crearMenu(menu: Menu)

    @Update
    suspend fun updateMenu(menu: Menu)

    @Delete
    suspend fun deleteMenu(menu: Menu)

    @Query("SELECT * FROM menus WHERE id = :menuId")
    suspend fun getMenuById(menuId: Int): Menu
}