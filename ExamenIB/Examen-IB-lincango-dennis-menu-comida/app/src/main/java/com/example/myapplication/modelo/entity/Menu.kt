@file:Suppress("SpellCheckingInspection")

package com.example.myapplication.modelo.entity

import java.time.LocalDateTime

class Menu(
    var id: Int,
    var nombre: String,
    var disponible: Boolean,
    var precio: Double,
    var calificacion: Int,
    var fechaAdicion: LocalDateTime,
) {

    override fun toString(): String {
        return  "\nNombre: ${nombre}\n" +
                "\nPrecio: ${precio}\n" +
                "\nDisponible: ${disponible}\n" +
                "\nCalificación: ${calificacion}\n" +
                "\nFecha adición: ${fechaAdicion}\n"
        /*"\nId: ${id}\n" +
                "Nombre: ${nombre}\n" +
                "Precio: ${precio}\n" +
                "Disponible: ${disponible}\n" +
                "Calificación: ${calificacion}\n" +
                "Fecha adición: ${fechaAdicion}\n"*/
    }

}