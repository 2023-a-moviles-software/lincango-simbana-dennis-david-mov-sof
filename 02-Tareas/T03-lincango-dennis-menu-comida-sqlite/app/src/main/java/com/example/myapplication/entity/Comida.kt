@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "comidas", foreignKeys = [ForeignKey(entity = Menu::class, parentColumns = ["id"], childColumns = ["menuId"])])
@Entity(tableName = "comidas")
data class Comida(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "nombre") var nombre: String,
    @ColumnInfo(name = "tipo") var tipo: String,
    @ColumnInfo(name = "descripcion")var descripcion: String,
    @ColumnInfo(name = "calorias")var calorias: Double,
    @ColumnInfo(name = "menuId" )var menuId: Int, // Clave externa para la relación uno a muchos
)