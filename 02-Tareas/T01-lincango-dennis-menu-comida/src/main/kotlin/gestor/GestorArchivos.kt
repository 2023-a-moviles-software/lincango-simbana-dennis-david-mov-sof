package gestor

import java.io.File
import java.io.FileWriter
import java.io.IOException

class GestorArchivos(private val fileName: String) {

    private val file: File = File(fileName)

    init {
        createFileIfNotExists()
    }

    private fun createFileIfNotExists() {
        try {
            if (!file.exists()) {
                file.createNewFile()
            }
        } catch (e: IOException) {
            println("Error al crear el archivos: ${e.message}")
        }
    }

    fun readData(): List<String> {
        return file.readLines()
    }

    fun writeData(data: List<String>) {
        try {
            val fileWriter = FileWriter(file)
            for (line in data) {
                fileWriter.write(line)
                fileWriter.write(System.lineSeparator())
            }
            fileWriter.close()
        } catch (e: IOException) {
            println("Error al escribir en el archivos: ${e.message}")
        }
    }

}
