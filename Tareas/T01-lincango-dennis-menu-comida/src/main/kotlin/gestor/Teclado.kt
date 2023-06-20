package gestor

import modelo.dao.ComidaDAO
import modelo.dao.MenuDAO
import modelo.entity.Menu
import modelo.entity.Comida
import java.time.LocalDate
import java.util.*
import kotlin.system.exitProcess

@Suppress("UNREACHABLE_CODE")
class Teclado {

    private val menuDAO: MenuDAO = MenuDAO(GestorArchivos("Tareas/T01-lincango-dennis-menu-comida/src/main/kotlin/archivos/menus.txt"))
    private val comidaDAO: ComidaDAO = ComidaDAO(GestorArchivos("Tareas/T01-lincango-dennis-menu-comida/src/main/kotlin/archivos/comidas.txt"))
    private val scanner: Scanner = Scanner(System.`in`)


    fun start() {
        var exit = false
        while (!exit) {
            println("======= Bienvenido a Mi Restaurante =======")
            println("1. Opciones menú")
            println("2. Opciones comida")
            println("3. Salir")
            println("==========================================")

            when (readMenuOption()) {
                1 -> startMenu()
                2 -> startComida()
                3 -> exit = true
                else -> println("Opción inválida. Ingrese nuevamente")
            }
            println()
        }
    }

    private fun startComida() {
        var exit = false
        while (!exit) {
            println("======= Menu de opciones =======")
            println("1. Crear Comida")
            println("2. Mostrar todas las comidas")
            println("3. Actualizar Comida")
            println("4. Eliminar Comida")
            println("5. Salir")
            println("================================")

            when (readMenuOption()) {
                1 -> createComida()
                2 -> showAllComidas()
                3 -> updateComida()
                4 -> deleteComida()
                5 -> exit = true
                else -> println("Opción inválida. Ingrese nuevamente")
            }
            println()
        }
    }

    private fun startMenu() {
        var exit = false
        while (!exit) {
            println("======= Menu de opciones =======")
            println("1. Crear Menu")
            println("2. Mostrar todos los Menus")
            println("3. Actualizar Menu")
            println("4. Eliminar Menu")
            println("5. Salir")
            println("================================")

            when (readMenuOption()) {
                1 -> createMenu()
                2 -> showAllMenus()
                3 -> updateMenu()
                4 -> deleteMenu()
                5 -> exit = true
                else -> println("Opción inválida. Ingrese nuevamente")
            }
            println()
        }
    }

    private fun readMenuOption(): Int {
        print("Ingresa una opción: ")
        val input = scanner.nextLine()
        return try {
            input.toInt()
        } catch (e: NumberFormatException) {
            println("Opción inválida. Cerrando la aplicación...")
            exitProcess(0)
            -1 // Valor de retorno para indicar un error
        }
    }

    private fun createComida() {
        println("==== Crear Comida ====")
        print("Ingresa el nombre de la comida: ")
        val nombre = scanner.nextLine()

        print("Ingresa el tipo de comida: ")
        val tipo = scanner.nextLine()

        print("Ingresa la descripción: ")
        val descripcion = scanner.nextLine()

        print("Ingresa las calorías: ")
        val calorias = scanner.nextInt()

        scanner.nextLine() // Limpiar el buffer del scanner

        print("Ingresa el número de ingredientes: ")
        val numeros = scanner.nextInt()

        print("Ingresa los ingredientes: ")
        val ingredientes = ArrayList<String>()

        for (i in 0 until numeros) {
            val elemento = scanner.next()
            ingredientes.add(elemento)
        }

        val comida = Comida(nombre, tipo, descripcion, calorias, ingredientes)

        comidaDAO.createComida(comida)
        println("\n\nComida creada exitosamente")

        scanner.nextLine() // Limpiar el buffer del scanner

    }

    private fun showAllComidas() {
        println("==== Mostrar todos las comidas ====")
        val comidas = comidaDAO.getAllComidas()

        if (comidas.isEmpty()) {
            println("No hay Comidas disponibles")
        } else {
            for (comida in comidas) {
                println(comida)
            }
        }
    }

    private fun updateComida() {
        println("==== Actualizar Comida ====")
        print("Ingresa el nombre de la comida a actualizar: ")
        val nombre = scanner.nextLine()

        print("Ingresa el tipo de comida: ")
        val tipo = scanner.nextLine()

        print("Ingresa la descripción: ")
        val descripcion = scanner.nextLine()

        print("Ingresa las calorías: ")
        val calorias = scanner.nextInt()

        scanner.nextLine() // Limpiar el buffer del scanner

        print("Ingresa el número de ingredientes: ")
        val numeros = scanner.nextInt()

        print("Ingresa los ingredientes: ")
        val ingredientes = ArrayList<String>()

        for (i in 0 until numeros) {
            val elemento = scanner.next()
            ingredientes.add(elemento)
        }

        // Crear un objeto Comida con las propiedades actualizadas
        val comida = Comida(nombre, tipo, descripcion, calorias, ingredientes)
        comidaDAO.updateComida(comida)
        println("\n\nComida actualizada exitosamente")

        scanner.nextLine() // Limpiar el buffer del scanner

    }

    private fun deleteComida() {
        println("==== Eliminar Comida ====")
        print("Ingresa el nombre de la Comida a eliminar: ")
        val nombre = scanner.nextLine()

        // Crear un objeto Menu solo con el nombre para eliminarlo
        val comida = Comida(nombre, "", "", 0, ArrayList<String>())
        comidaDAO.deleteComida(comida)
        println("\nComida eliminada exitosamente")
    }

    private fun createMenu() {
        println("==== Crear Menu ====")
        print("Ingresa el nombre del Menu: ")
        val nombre = scanner.nextLine()

        print("Ingresa la disponibilidad (true/false): ")
        val disponible = scanner.nextBoolean()

        print("Ingresa el precio: ")
        val precio = scanner.nextDouble()

        print("Ingresa la calificación: ")
        val calificacion = scanner.nextInt()

        scanner.nextLine() // Limpiar el buffer del scanner

        //Fecha establecida automaticamente por el sistema
        val fecha = LocalDate.now()

        // Crear un objeto Menu con todas las propiedades
        val menu = Menu(nombre, disponible, precio, calificacion, fecha)
        menuDAO.createMenu(menu)
        println("\n\nMenú creado exitosamente")
    }

    private fun showAllMenus() {
        println("==== Mostrar todos los Menus ====")
        val menus = menuDAO.getAllMenus()

        if (menus.isEmpty()) {
            println("No hay Menus disponibles")
        } else {
            for (menu in menus) {
                println(menu)
            }
        }
    }

    private fun updateMenu() {
        println("==== Actualizar Menu ====")
        print("Ingresa el nombre del Menu a actualizar: ")
        val nombre = scanner.nextLine()

        print("Ingresa la disponibilidad (true/false): ")
        val disponible = scanner.nextBoolean()

        print("Ingresa el precio: ")
        val precio = scanner.nextDouble()

        print("Ingresa la calificación: ")
        val calificacion = scanner.nextInt()

        scanner.nextLine() // Limpiar el buffer del scanner

        // Fecha establecida automaticamente por el sistema
        val fecha = LocalDate.now()

        // Crear un objeto Menu con las propiedades actualizadas
        val menu = Menu(nombre, disponible, precio, calificacion, fecha)
        menuDAO.updateMenu(menu)
        println("\n\nMenú actualizado exitosamente")
    }

    private fun deleteMenu() {
        println("==== Eliminar Menu ====")
        print("Ingresa el nombre del Menu a eliminar: ")
        val nombre = scanner.nextLine()

        // Crear un objeto Menu solo con el nombre para eliminarlo
        val menu = Menu(nombre, false, 0.0, 0, LocalDate.now())
        menuDAO.deleteMenu(menu)
        println("\nMenu eliminado exitosamente")
    }

}
