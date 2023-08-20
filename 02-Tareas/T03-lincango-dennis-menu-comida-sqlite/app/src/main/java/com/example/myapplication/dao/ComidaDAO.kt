@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.entity.Comida

@Dao
interface ComidaDAO {

    @Query("SELECT * FROM comidas")
    suspend fun getComidas(): MutableList<Comida>

    @Insert
    suspend fun createComida(comida: Comida)

    @Update
    suspend fun updateComida(comida: Comida)

    @Query("DELETE FROM comidas WHERE id=:id")
    suspend fun deleteComida(id: Int)

    @Query("SELECT * FROM comidas WHERE menuId = :menuId")
    suspend fun getComidasByMenuId(menuId: Int): List<Comida>

}