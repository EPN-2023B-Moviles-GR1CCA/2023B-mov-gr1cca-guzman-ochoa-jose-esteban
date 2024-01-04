// By: José Guzmán

fun main() {
    val variable: String = "Adrian Eguez";
    /*
    * val -> inmutable
    * var -> mutable
    *
    * Duck typing
    *
    *
    * */

    when (variable) {
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No se sabe")
        }
    }

    val coqueteo = if (variable == "S") "si" else "No"

    println("Hello World!")

    calcularSueldo(10.00)
    calcularSueldo(tasa = 14.00, sueldo = 12.00)
    calcularSueldo(sueldo = 12.00, bonoEspecial = 15.00, tasa = 14.00)

    calcularSueldo(10.00, bonoEspecial = 15.00)
}

fun imrpimirNombre(nombre: String): Unit {
    println("Nombre: $nombre")
}

fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null
): Double {
    if (bonoEspecial != null) {
        return sueldo * tasa + bonoEspecial
    } else {
        bonoEspecial.dec()
        return sueldo * tasa
    }
}

// clase en Java
abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ) {
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializar")
    }
}

abstract class Numeros(
    protected var numeroUno: Int,
    protected var numeroDos: Int
) {
    init {
        this.numeroUno; this.numeroDos;
        numeroUno; numeroDos;
        println("Inicializar")
    }
}

class Suma(
    uno: Int,
    dos: Int
): Numeros(uno, dos) {
    init {
        this.numeroUno; numeroUno;
        this.numeroDos; numeroDos;
    }

    constructor(
        uno: Int?,
        dos: Int
    ) : this(
        if (uno == null) 0 else uno,
        dos
    ) {
        numeroUno;
    }

    constructor(
        uno: Int,
        dos: Int?
    ) : this(
        uno,
        if (dos == null) 0 else uno
    )
    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    companion object {
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }

    val lista = listOf("a", "b", "c")

    public fun prueba(): Unit {
        lista.forEachIndexed { index, elemento ->
        println("Elemento en el índice $index es $elemento")
    }
        data class Libro(val titulo: String, val añoPublicacion: Int, val numeroPaginas: Int)

        val libros = listOf(
            Libro("El Señor de los Anillos", 1954, 1178),
            Libro("Cien años de soledad", 1967, 471),
            Libro("Harry Potter y la piedra filosofal", 1997, 223),
            Libro("La sombra del viento", 2001, 576)
        )

        val librosFiltrados = libros.filter { it.añoPublicacion > 2000 && it.numeroPaginas > 300 }
            .map { "${it.titulo} (${it.añoPublicacion})" }

        println(librosFiltrados)

        val numeros = listOf(1, 2, 3, 4, 5)
        val suma = numeros.reduce { acumulado, actual -> acumulado + actual }
        println(suma)


    }




val suma = Suma(null, 1)

}


val valorPi = Suma.pi
val cuadradoDeCuatro = Suma.elevarAlCuadrado(4)
val resultado = Suma.agregarHistorial(10)