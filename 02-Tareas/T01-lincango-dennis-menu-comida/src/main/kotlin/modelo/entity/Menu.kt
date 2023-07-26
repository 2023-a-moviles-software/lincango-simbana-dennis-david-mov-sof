package modelo.entity

import java.time.LocalDate

data class Menu(
    val nombre: String,
    var disponible: Boolean,
    var precio: Double,
    var calificacion: Int,
    var fechaAdicion: LocalDate
)
