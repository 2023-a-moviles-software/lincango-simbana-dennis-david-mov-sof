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
    calcularSueldo(10.00, 12.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //Parametros nombrados

    val sumaUno = Suma(1, 1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    // ARREGLOS
    // Tipos de arreglos

    // Arreglo Estático
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

    // Arreglo Dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // OPERADORES -> Sirven para los areglos estáticos y dinámicos
    // FOR EACH -> Unit
    val respuestaForEach: Unit = arregloDinamico
        .forEach{ valorActual:Int ->
            println("Valor actual: ${valorActual}")
        }

    //Nos devuelve el valor que está iterando:
    arregloDinamico.forEach{ println(it) } // it (en inglés eso) significa el elemento iterado

    arregloEstatico
        .forEachIndexed{ indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la itearación
    // 2) Nos devuelve en un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }

    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15}

    // Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresión (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5 // Expresión Condición
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    // OR -> ANY (ALGUNO CUMPLE?) -> Boolean
    // AND -> ALL (TODOS CUMPLEN?) -> Boolean

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // false

    // REDUCE -> VALOR ACUMULADO
    // Valor acumulado = 0 (Siempre 0 en lenguaje de kotlin)
    // [1,2,3,4,5] -> Sumemem todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteración 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteración 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteración 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteración 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteración 5

    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> Siempre empieza en 0
                valorAcumulado: Int, valorActual: Int ->
            return@reduce (valorAcumulado + valorActual) // Lógica negocio
        }
    println(respuestaReduce)

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


abstract class Numeros(
    // Constructor PRIMARIO
    // Ejemplo:
    // uno: Int, (Parámetro (sin modificar acceso))
    // private var uno: Int, // Propiedad Publiva Clase numeros.uno
    // var uno: Int, // Propiedad de la clase (por defecto es Public)
    // public var uno: Int,
    protected val numeroUno: Int, // Propiedad de la clase protected numeros.numemorUno
    protected val numeroDos: Int, // Propiedad de la clase protected nomeros.numeorDos
) {
    // var cedula: String =  "" (public es por defecto)
    // private valorCalculado: Int = 0 (private)
    init {
        this.numeroUno; this.numeroDos // this es opcional
        numeroUno; numeroDos; // sin el "this", es lo mismo
        println("Inicializando")
    }
}

class Suma(
    //Constructor primario Suma
    unoParametro: Int, // Parámetro
    dosParametro: Int, // Parámetro
) : Numeros(unoParametro, dosParametro) { // Extendiendo y mandando los parámetros (super)
    init { // Bloque código constructor primario
        this.numeroUno
        this.numeroDos
    }

    constructor( // Segundo constructor
        uno: Int?, // Parametros
        dos: Int // Parametros
    ) : this(
        if (uno == null) 0 else uno,
        dos
    )

    constructor( // Tercer constructor
        uno: Int, // Parametros
        dos: Int? // Parametros
    ) : this(
        uno,
        if (dos == null) 0 else dos,
    )

    constructor( // Cuarto constructor
        uno: Int?, // Parametros
        dos: Int? // Parametros
    ) : this(
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    )

    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object { // Atributos y métodos "Compartidos" Singletons o Static de esta clase
        // Todas las instancias de esta clase comparten estos atributos y métodos dentro del companion object
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }

        val historialSumas = ArrayList<Int>()

        fun agregarHistorial(valorNuevaSuma: Int) {
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





