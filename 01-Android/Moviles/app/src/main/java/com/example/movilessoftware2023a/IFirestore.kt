@file:Suppress("spellCheckingInspection")
package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date

class IFirestore : AppCompatActivity() {

    val query: Query? = null
    val arreglo: ArrayList<ICities> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirestore)
        // Configurando el list view
        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            arreglo // Arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        // Crear datos
        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener { crearDatosPrueba() }
        // Order by
        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener { consultarConOrderBy(adaptador) }
        // Obtener documento
        val botonObtenerDocumento = findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerDocumento.setOnClickListener { consultarDcocumento(adaptador) }
        // Consultar índice compuesto
        val botonIndiceCompuesto = findViewById<Button>(R.id.btn_fs_ind_comp)
        botonIndiceCompuesto.setOnClickListener { consultarIndiceCompuesto(adaptador) }
        // Datos de prueba
        val botonCrear = findViewById<Button>(R.id.btn_fs_crear)
        botonCrear.setOnClickListener { crearEjemplo() }
        // Eliminar
        val botonEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonEliminar.setOnClickListener { eliminar() }

    }

    private fun eliminar() {
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db.collection("ejemplo")
        referenciaEjemploEstudiante
            .document("12345678") // id usado para crear en la función "crearEjemplo"
            .delete() // elimina
            .addOnCompleteListener {  }
            .addOnSuccessListener {  }
     }

    private fun crearEjemplo() {
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db
            .collection("ejemplo")
        // .document("id_hijo")
        //.collection("estudiante")
        val identificador = Date().time
        val datosEstudiante = hashMapOf(
            "nombre" to "Dennis",
            "graduado" to false,
            "promedio" to 9.8,
            "direccion" to hashMapOf(
                "direccion" to "El Inca",
                "numeroCalle" to 1234
            ),
            "materias" to listOf("web", "moviles")
        )


        //  identificador quemado (crear/actualizar)
        referenciaEjemploEstudiante
            .document("12345678")
            .set(datosEstudiante)
            .addOnSuccessListener { }
            .addOnFailureListener { }
        // identificador quemado pero autogenerado con Date().time
        referenciaEjemploEstudiante // (crear/actualizar)
            .document(identificador.toString())
            .set(datosEstudiante)
            .addOnSuccessListener { }
            .addOnFailureListener { }
        // Sin identificador (crear)
        referenciaEjemploEstudiante
            .add(datosEstudiante)
            .addOnCompleteListener{ }
            .addOnFailureListener{}
    }



    private fun consultarIndiceCompuesto(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        citiesRefUnico
            .whereEqualTo("capital", false)
            .whereLessThanOrEqualTo("population", 4000000)
            .get()
            .addOnSuccessListener {
                for (ciudad in it) {
                    anadirAArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener { }
    }

    private fun consultarDcocumento(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        // Colección "ciudad"
        // -> Colección Barrio
        //      -> Colección dirección
        // "Quito" => "La Floresta" => "E90-001"
        // db.collection("ciudad").document("Quito").collection("barrio")
        //   .collectio("barrio").document("La Floresta").collection("direccion")
        //   .document("E90-001")
        // .collection("nombre_coleccion_hijo").document("id_hijo")
        // .collection("nombre_collection_nieto").document("id_nieto")
        citiesRefUnico
            .document("BJ")
            .get() // obtener 1 Documento
            .addOnSuccessListener {
                // it => Es un objeto
                arreglo
                    .add(
                        ICities(
                            it.data?.get("name") as String?,
                            it.data?.get("state") as String?,
                            it.data?.get("country") as String?,
                            it.data?.get("capital") as Boolean?,
                            it.data?.get("population") as Long?,
                            it.data?.get("regions") as ArrayList<String>?
                        )
                    )
            }
    }

    private fun consultarConOrderBy(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico
            .orderBy("population", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it) {
                    ciudad.id
                    anadirAArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                // Errores
            }

    }

    private fun limpiarArreglo() {
        arreglo.clear()
    }

    private fun anadirAArregloCiudad(
        ciudad: QueryDocumentSnapshot
    ) {
        // ciudad.id
        val nuevaCiudad = ICities(
            ciudad.data.get("name") as String?,
            ciudad.data.get("state") as String?,
            ciudad.data.get("country") as String?,
            ciudad.data.get("capital") as Boolean?,
            ciudad.data.get("population") as Long?,
            ciudad.data.get("regions") as ArrayList<String>?
        )
        arreglo.add(nuevaCiudad)
    }

    fun crearDatosPrueba() {
        val db = Firebase.firestore

        val cities = db.collection("cities")
        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal"),
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal"),
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast"),
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu"),
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei"),
        )
        cities.document("BJ").set(data5)

    }

}
