import java.time.LocalDate

class CLI {
    private val crudService = CRUDService()

    fun iniciar() {
        var opcion: String
        do {
            mostrarMenuPrincipal()
            println("Seleccione una opción: ")
            opcion = readLine() ?: ""
            manejarOpcion(opcion)
        } while (opcion != "0")
    }

    private fun mostrarMenuPrincipal() {
        println("\n---- Menú Principal ----")
        println("1. Operaciones con Marcas de Computadores")
        println("2. Operaciones con Computadores")
        println("0. Salir")
    }

    private fun manejarOpcion(opcion: String) {
        when (opcion) {
            "1" -> menuMarcas()
            "2" -> menuComputadores()
            "0" -> println("Saliendo del programa...")
            else -> println("Opción no válida, intente de nuevo.")
        }
    }

    private fun menuMarcas() {
        println("\n---- Menú Marcas de Computadores ----")
        println("1. Agregar nueva marca")
        println("2. Mostrar todas las marcas")
        println("3. Actualizar una marca")
        println("4. Eliminar una marca")
        println("0. Volver al menú principal")

        when (readLine()) {
            "1" -> addNuevaMarca()
            "2" -> mostrarMarcas()
            "3" -> actualizarMarca()
            "4" -> eliminarMarca()
            "0" -> return
            else -> println("Opción no válida, intente de nuevo.")
        }
    }

    private fun menuComputadores() {
        println("\n---- Menú Computadores ----")
        println("1. Agregar nuevo computador")
        println("2. Mostrar todos los computadores")
        println("3. Actualizar un computador")
        println("4. Eliminar un computador")
        println("0. Volver al menú principal")

        when (readLine()) {
            "1" -> addNuevoComputador()
            "2" -> mostrarComputadores()
            "3" -> actualizarComputador()
            "4" -> eliminarComputador()
            "0" -> return
            else -> println("Opción no válida, intente de nuevo.")
        }
    }

    private fun addNuevaMarca() {
        println("Ingrese el nombre de la marca:")
        val nombre = readLine() ?: return
        println("Ingrese el país de origen:")
        val paisOrigen = readLine() ?: return
        println("Ingrese el año de fundación:")
        val añoFundacion = readLine()?.toIntOrNull() ?: return
        println("¿Es independiente? (si/no):")
        val esIndependiente = readLine()?.equals("si", ignoreCase = true) ?: return

        val nuevaMarca = MarcaComputador(nombre, paisOrigen, añoFundacion, esIndependiente, LocalDate.now())
        crudService.addMarca(nuevaMarca)
        println("Marca añadida con éxito.")
    }

    private fun mostrarMarcas() {
        val marcas = crudService.leerMarcas()
        if (marcas.isEmpty()) {
            println("No hay marcas registradas.")
        } else {
            marcas.forEach { println(it) }
        }
    }

    private fun actualizarMarca() {
        println("Ingrese el nombre de la marca a actualizar:")
        val nombre = readLine() ?: return

        val marcaExistente = crudService.leerMarcas().find { it.nombre.equals(nombre, ignoreCase = true) }
        if (marcaExistente == null) {
            println("Marca no encontrada.")
            return
        }

        println("Ingrese el nuevo país de origen (anterior: ${marcaExistente.paisOrigen}):")
        val nuevoPaisOrigen = readLine() ?: marcaExistente.paisOrigen
        println("Ingrese el nuevo año de fundación (anterior: ${marcaExistente.añoFundacion}):")
        val nuevoAñoFundacion = readLine()?.toIntOrNull() ?: marcaExistente.añoFundacion
        println("¿Es independiente? (si/no, anterior: ${if (marcaExistente.esIndependiente) "si" else "no"}):")
        val nuevoEsIndependiente = readLine()?.equals("si", ignoreCase = true) ?: marcaExistente.esIndependiente

        val marcaActualizada = MarcaComputador(
            nombre = nombre,
            paisOrigen = nuevoPaisOrigen,
            añoFundacion = nuevoAñoFundacion,
            esIndependiente = nuevoEsIndependiente,
            fechaRegistro = marcaExistente.fechaRegistro,
            computadores = marcaExistente.computadores
        )

        crudService.actualizarMarca(nombre, marcaActualizada)
        println("Marca actualizada con éxito.")
    }

    private fun eliminarMarca() {
        println("Ingrese el nombre de la marca a eliminar:")
        val nombre = readLine() ?: return
        crudService.eliminarMarca(nombre)
        println("Marca eliminada con éxito.")
    }

    private fun addNuevoComputador() {
        println("Ingrese el modelo del computador:")
        val modelo = readLine() ?: return
        println("Ingrese el precio del computador:")
        val precio = readLine()?.toDoubleOrNull() ?: return
        println("Ingrese el tipo de computador (PORTATIL/ESCRITORIO):")
        val tipoInput = readLine()
        val tipo = when(tipoInput?.toUpperCase()) {
            "PORTATIL" -> TipoComputador.PORTATIL
            "ESCRITORIO" -> TipoComputador.ESCRITORIO
            else -> return
        }
        println("Ingrese el año de lanzamiento:")
        val añoLanzamiento = readLine()?.toIntOrNull() ?: return
        println("¿Está en producción? (si/no):")
        val enProduccion = readLine()?.equals("si", ignoreCase = true) ?: return
        println("Ingrese el nombre de la marca a la que pertenece el computador:")
        val nombreMarca = readLine() ?: return

        val marcaExistente = crudService.leerMarcas().find { it.nombre.equals(nombreMarca, ignoreCase = true) }
        if (marcaExistente == null) {
            println("Marca no encontrada. Por favor, añada primero la marca.")
            return
        }

        val nuevoComputador = Computador(modelo, precio, tipo, añoLanzamiento, enProduccion, nombreMarca)

        marcaExistente.computadores.add(nuevoComputador)

        crudService.actualizarMarca(nombreMarca, marcaExistente)

        crudService.addComputador(nuevoComputador)

        println("Computador añadido con éxito.")
    }

    private fun mostrarComputadores() {
        val computadores = crudService.leerComputadores()
        if (computadores.isEmpty()) {
            println("No hay computadores registrados.")
        } else {
            computadores.forEach { computador ->
                println("Modelo: ${computador.modelo}")
                println("Precio: ${computador.precio}")
                println("Tipo: ${computador.tipo}")
                println("Año de Lanzamiento: ${computador.añoLanzamiento}")
                println("En Producción: ${if (computador.enProduccion) "Sí" else "No"}")
                println("Marca: ${computador.nombreMarca}")
                println("----")
            }
        }
    }

    private fun actualizarComputador() {
        println("Ingrese el modelo del computador a actualizar:")
        val modelo = readLine() ?: return

        val computadorExistente = crudService.leerComputadores().find { it.modelo.equals(modelo, ignoreCase = true) }
        if (computadorExistente == null) {
            println("Computador no encontrado.")
            return
        }

        println("Ingrese el nuevo precio (precio anterior: ${computadorExistente.precio}):")
        val nuevoPrecio = readLine()?.toDoubleOrNull() ?: computadorExistente.precio
        println("Ingrese el nuevo tipo (PORTATIL/ESCRITORIO, tipo anterior: ${computadorExistente.tipo}):")
        val nuevoTipoInput = readLine()
        val nuevoTipo = when(nuevoTipoInput?.toUpperCase()) {
            "PORTATIL" -> TipoComputador.PORTATIL
            "ESCRITORIO" -> TipoComputador.ESCRITORIO
            else -> computadorExistente.tipo
        }
        println("Ingrese el nuevo año de lanzamiento (año anterior: ${computadorExistente.añoLanzamiento}):")
        val nuevoAñoLanzamiento = readLine()?.toIntOrNull() ?: computadorExistente.añoLanzamiento
        println("¿Está en producción? (si/no, estado anterior: ${if (computadorExistente.enProduccion) "si" else "no"}):")
        val nuevoEnProduccion = readLine()?.equals("si", ignoreCase = true) ?: computadorExistente.enProduccion

        val computadorActualizado = Computador(
            modelo = modelo,
            precio = nuevoPrecio,
            tipo = nuevoTipo,
            añoLanzamiento = nuevoAñoLanzamiento,
            enProduccion = nuevoEnProduccion,
            nombreMarca = computadorExistente.nombreMarca
        )

        crudService.actualizarComputador(modelo, computadorActualizado)
        println("Computador actualizado con éxito.")
    }

    private fun eliminarComputador() {
        println("Ingrese el modelo del computador a eliminar:")
        val modelo = readLine() ?: return

        val computadorExistente = crudService.leerComputadores().any { it.modelo.equals(modelo, ignoreCase = true) }
        if (!computadorExistente) {
            println("Computador no encontrado.")
            return
        }

        crudService.eliminarComputador(modelo)
        println("Computador eliminado con éxito.")
    }
}
