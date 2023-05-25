import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")


    // INMUTABLES (NO se reasignan "=")
    val inmutable: String = "Dennis";
    // inmutable = "David";

    // Mutables (Re asignar)
    var mutable: String = "Dennis";
    mutable = " David";

    //Es preferible utilizar variables inmutables a caomparación de variables mutables

    // Duck Typing
    var ejemploVariable = "Dennis Lincango"
    val edadEjemplo: Int = 12
    ejemploVariable.trim()
    // ejempñoVariable = edadEjemplo;

    // Variable primitiva
    val nombreProfesor: String = "Dennis Lincango"
    val suekdo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true

    // Clases Java
    val fechaNacimiento: Date = Date()

    // Dentro de Kotlin no existe Switch, existe when
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00,12.00,20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //Parametros nombrados

}

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ) {
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}


abstract class Numeros( // Constructor PRIMARIO
    // Ejemplo:
    // uno: Int, (Parámetro (sin modificar acceso))
    // private var uno: Int, // Propiedad Publiva Clase numeros.uno
    // var uno: Int, // Propiedad de la clase (por defecto es Public)
    // public var uno: Int,
    protected val numeroUno: Int, // Propiedad de la clase protected numeros.numemorUno
    protected val numeroDos: Int, // Propiedad de la clase protected nomeros.numeorDos
){
    // var cedula: String =  "" (public es por defecto)
    // private valorCalculado: Int = 0 (private)
    init{
        this.numeroUno; this.numeroDos // this es opcional
        numeroUno; numeroDos; // sin el "this", es lo mismo
        println("Inicializando")
    }
}

    // En Kotlin NO existe el void, existe el unit

    fun imprimirNombre(nombre: String): Unit {
        println("Nombre : ${nombre}") //template strings
    }

    fun calcularSueldo(
        sueldo: Double, // Requerido
        tasa: Double = 12.00, // Opcional (defecto)
        bonoEspecial: Double? = null, // Opción null -->  nullable
    ): Double {
        // Int --> Int? (nullable)
        // String --> String? (nullable)
        if (bonoEspecial == null) {
            return sueldo * (100 / tasa)
        } else {
            return sueldo * (100 / tasa) + bonoEspecial
        }
    }





