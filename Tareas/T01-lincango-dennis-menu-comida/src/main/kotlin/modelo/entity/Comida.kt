package modelo.entity

data class Comida(
    val nombre: String,
    var tipo: String,
    var descripcion: String,
    var calorias: Int,
    var ingredientes: List<String>
)
