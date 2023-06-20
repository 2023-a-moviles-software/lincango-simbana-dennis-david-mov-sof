package modelo.dao

import gestor.GestorArchivos
import modelo.entity.Comida

class ComidaDAO(private val fileHandler: GestorArchivos) {

    fun createComida(comida: Comida) {
        val comidaList = getAllComidas()
        comidaList.add(comida)
        saveAllComidas(comidaList)
    }

    fun getAllComidas(): MutableList<Comida> {
        val comidaData = fileHandler.readData()
        val comidaList = mutableListOf<Comida>()
        for (line in comidaData) {
            val comidaProperties = line.split(",")
            val ingredientes = comidaProperties.subList(4, comidaProperties.size)
            val comida = Comida(
                comidaProperties[0],
                comidaProperties[1],
                comidaProperties[2],
                comidaProperties[3].toInt(),
                ingredientes
            )
            comidaList.add(comida)
        }
        return comidaList
    }

    fun updateComida(comida: Comida) {
        val comidaList = getAllComidas()
        val existingComida = comidaList.find { it.nombre == comida.nombre }
        if (existingComida != null) {
            existingComida.tipo = comida.tipo
            existingComida.descripcion = comida.descripcion
            existingComida.calorias = comida.calorias
            existingComida.ingredientes = comida.ingredientes
            saveAllComidas(comidaList)
        }
    }

    fun deleteComida(comida: Comida) {
        val comidaList = getAllComidas()
        comidaList.removeIf { it.nombre == comida.nombre }
        saveAllComidas(comidaList)
    }

    private fun saveAllComidas(comidaList: List<Comida>) {
        val comidaData = comidaList.map { comida ->
            val ingredientes = comida.ingredientes.joinToString(",")
            "${comida.nombre},${comida.tipo},${comida.descripcion},${comida.calorias},$ingredientes"
        }
        fileHandler.writeData(comidaData)
    }

}
