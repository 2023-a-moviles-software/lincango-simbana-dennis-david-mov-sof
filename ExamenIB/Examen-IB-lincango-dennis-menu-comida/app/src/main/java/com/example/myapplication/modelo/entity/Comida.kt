package com.example.myapplication.modelo.entity

class Comida(
    var id: Int,
    var nombre: String,
    var tipo: String,
    var descripcion: String,
    var calorias: Double,
    var ingredientes: List<String>,
    var refrenciaIdMenu: Int = -1
) {

    override fun toString(): String {
        return  "\nNombre: ${nombre}\n" +
                "\nTipo: ${tipo}\n" +
                "\nDescripción: ${descripcion}\n" +
                "\nCalorías: ${calorias}\n" +
                "\nIngredientes: ${ingredientes}\n"
        /*
        "\nId: ${id}\n" +
                "Nombre: ${nombre}\n" +
                "Tipo: ${tipo}\n" +
                "Descripción: ${descripcion}\n" +
                "Calorías: ${calorias}\n" +
                "Ingredientes: ${ingredientes}\n"
         */
    }
}