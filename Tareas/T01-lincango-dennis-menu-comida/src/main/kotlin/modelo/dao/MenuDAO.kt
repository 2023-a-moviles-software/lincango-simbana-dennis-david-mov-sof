package modelo.dao

import gestor.GestorArchivos
import modelo.entity.Menu
import java.time.LocalDate

class MenuDAO(private val fileHandler: GestorArchivos) {

    fun createMenu(menu: Menu) {
        val menuList = getAllMenus()
        menuList.add(menu)
        saveAllMenus(menuList)
    }

    fun getAllMenus(): MutableList<Menu> {
        val menuData = fileHandler.readData()
        val menuList = mutableListOf<Menu>()
        for (line in menuData) {
            val menuProperties = line.split(",")
            if (menuProperties.size >= 5) { // Verificar que haya al menos 5 elementos
                val menu = Menu(
                    menuProperties[0],
                    menuProperties[1].toBoolean(),
                    menuProperties[2].toDouble(),
                    menuProperties[3].toInt(),
                    LocalDate.parse(menuProperties[4])
                )
                menuList.add(menu)
            }
        }
        return menuList
    }

    fun updateMenu(menu: Menu) {
        val menuList = getAllMenus()
        val existingMenu = menuList.find { it.nombre == menu.nombre }
        if (existingMenu != null) {
            existingMenu.disponible = menu.disponible
            existingMenu.precio = menu.precio
            existingMenu.calificacion = menu.calificacion
            existingMenu.fechaAdicion = menu.fechaAdicion
            saveAllMenus(menuList)
        }
    }

    fun deleteMenu(menu: Menu) {
        val menuList = getAllMenus()
        menuList.removeIf { it.nombre == menu.nombre }
        saveAllMenus(menuList)
    }

    private fun saveAllMenus(menuList: List<Menu>) {
        val menuData = menuList.map { menu ->
            "${menu.nombre},${menu.disponible},${menu.precio},${menu.calificacion},${menu.fechaAdicion}"
        }
        fileHandler.writeData(menuData)
    }

}
