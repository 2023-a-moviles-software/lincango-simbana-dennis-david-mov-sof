@file:Suppress("SpellCheckingInspection")
package com.example.myapplication.almacenamiento

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.modelo.entity.Comida
import com.example.myapplication.modelo.entity.Menu
import java.time.LocalDateTime

class BaseDatosMemoria {

    @RequiresApi(Build.VERSION_CODES.O)
    companion object {
        val listaComidas = mutableListOf<Comida>()
        val listaMenus = mutableListOf<Menu>()

        init {
            // Creación de comidas
            listaComidas.add(
                Comida(
                    1,
                    " Pollo al Curry",
                    "Pollo",
                    "ugosos trozos de pollo cocinados en una sabrosa salsa de curry, servidos con arroz basmati y pan naan recién horneado.",
                    550.0,
                    listOf("Pollo", "cebolla", "ajo", "jengibre", "tomate", "crema de coco", "especias de curry", "arroz basmati", "harina", "levadura", "leche", "mantequilla"),
                    1
                )
            )

            listaComidas.add(
                Comida(
                    2,
                    "Ensalada Mediterránea",
                    "Ensalada",
                    "Ensalada fresca con hojas de lechuga, pepino, tomate, aceitunas, queso feta y aderezo de vinagreta de limón.",
                    300.0,
                    listOf("Lechuga", "pepino", "tomate", "aceitunas", "queso feta", "aceite de oliva", "vinagre de vino tinto", "limón."),
                    2
                )
            )

            listaComidas.add(
                Comida(
                    3,
                    "Vegetales al Wok",
                    "Vegetariano",
                    "Mezcla de vegetales salteados al wok con salsa de soja y jengibre, servidos con fideos de arroz.",
                    420.0,
                    listOf("Brócoli", "zanahoria", "pimiento", "champiñones", "salsa de soja", "jengibre", "ajo", "fideos de arroz."),
                    2
                )
            )

            listaComidas.add(
                Comida(
                    4,
                    "Hamburguesa de Garbanzos",
                    "Vegetariano",
                    "Deliciosa hamburguesa hecha a base de garbanzos, acompañada de lechuga, tomate, cebolla y una cremosa salsa de yogur.",
                    380.0,
                    listOf("Garbanzos cocidos", "cebolla", "ajo", "comino", "pimentón", "pan rallado", "lechuga", "tomate", "cebolla", "yogur", "pepino."),
                    2
                )
            )

            listaComidas.add(
                Comida(
                    5,
                    "Pizza Margarita",
                    "Pizza",
                    "Clásica pizza italiana con tomate, mozzarella y albahaca fresca, horneada en horno de leña para obtener una corteza crujiente.",
                    520.0,
                    listOf("Masa de pizza", "salsa de tomate", "mozzarella", "albahaca fresca", "aceite de oliva."),
                    3
                )
            )

            listaComidas.add(
                Comida(
                    6,
                    "Lasagna ",
                    "Pasta",
                    "Capas de pasta al huevo intercaladas con salsa boloñesa, bechamel y queso parmesano, gratinadas al horno.",
                    620.0,
                    listOf("Pasta al huevo", "carne de res", "salsa de tomate", "cebolla", "zanahoria", "apio", "leche", "harina", "mantequilla", "queso parmesano."),
                    3
                )
            )

            // Creación de menús con sus respectivas comidas
            val menu1 = Menu(
                1,
                "Menú Ejecutivo",
                true,
                15.99,
                5,
                LocalDateTime.now()
            )
            listaMenus.add(menu1)

            val menu2 = Menu(
                2,
                "Menú Vegetariano",
                true,
                12.50,
                4,
                LocalDateTime.now()
            )

            listaMenus.add(menu2)

            val menu3 = Menu(
                3,
                "Menú Italiano",
                true,
                16.50,
                4,
                LocalDateTime.now()
            )

            listaMenus.add(menu3)
        }
    }
}
