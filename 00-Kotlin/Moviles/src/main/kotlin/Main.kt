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

    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)
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

class Suma( //Constructor primario Suma
    unoParametro: Int, // Parámetro
    dosParametro: Int, // Parámetro
): Numeros(unoParametro, dosParametro){ // Extendiendo y mandando los parámetros (super)
    init{ // Bloque código constructor primario
        this.numeroUno
        this.numeroDos
    }

    constructor( // Segundo constructor
        uno: Int?, // Parametros
        dos: Int // Parametros
 ):this(
        if(uno == null) 0 else uno,
        dos
 )

    constructor( // Tercer constructor
        uno: Int, // Parametros
        dos: Int? // Parametros
    ):this(
        uno,
        if(dos == null) 0 else dos,
    )

    constructor( // Cuarto constructor
        uno: Int?, // Parametros
        dos: Int? // Parametros
    ):this(
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos,
    )

    public fun sumar(): Int{
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object{ // Atributos y métodos "Compartidos" Singletons o Static de esta clase
        // Todas las instancias de esta clase comparten estos atributos y métodos dentro del companion object
        val pi = 3.14
        fun elevarAlCuadrado(num: Int):Int{
            return num* num
        }

        val historialSumas = ArrayList<Int>()

        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
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





