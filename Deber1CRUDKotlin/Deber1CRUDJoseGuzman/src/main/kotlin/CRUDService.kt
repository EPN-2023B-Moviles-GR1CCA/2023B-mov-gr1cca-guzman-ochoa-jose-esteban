import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class CRUDService {
    private val marcasFileName = "marcas.json"
    private val computadoresFileName = "computadores.json"
    private val gson = Gson()

    fun addMarca(marca: MarcaComputador) {
        val marcas = leerMarcas().toMutableList()
        marcas.add(marca)
        guardarMarcas(marcas)
    }

    fun leerMarcas(): List<MarcaComputador> {
        val typeToken = object : TypeToken<List<MarcaComputador>>() {}
        return leerArchivo(marcasFileName, typeToken)
    }

    fun actualizarMarca(nombre: String, nuevaMarca: MarcaComputador) {
        val marcas = leerMarcas().toMutableList()
        val index = marcas.indexOfFirst { it.nombre == nombre }
        if (index != -1) {
            marcas[index] = nuevaMarca
            guardarMarcas(marcas)
        }
    }

    fun eliminarMarca(nombre: String) {
        val marcas = leerMarcas().filter { it.nombre != nombre }
        guardarMarcas(marcas)
    }

    private fun guardarMarcas(marcas: List<MarcaComputador>) {
        guardarArchivo(marcasFileName, marcas)
    }

    fun addComputador(computador: Computador) {
        val computadores = leerComputadores().toMutableList()
        computadores.add(computador)
        guardarComputadores(computadores)
    }

    fun leerComputadores(): List<Computador> {
        val typeToken = object : TypeToken<List<Computador>>() {}
        return leerArchivo(computadoresFileName, typeToken)
    }

    fun actualizarComputador(modelo: String, nuevoComputador: Computador) {
        val computadores = leerComputadores().toMutableList()
        val index = computadores.indexOfFirst { it.modelo == modelo }
        if (index != -1) {
            computadores[index] = nuevoComputador
            guardarComputadores(computadores)
        }
    }

    fun eliminarComputador(modelo: String) {
        val computadores = leerComputadores().filter { it.modelo != modelo }
        guardarComputadores(computadores)
    }

    fun guardarComputadores(computadores: List<Computador>) {
        guardarArchivo(computadoresFileName, computadores)
    }

    fun <T> leerArchivo(fileName: String, typeToken: TypeToken<T>): T {
        val file = File(fileName)
        if (!file.exists()) return listOf<T>() as T
        val json = file.readText()
        return Gson().fromJson(json, typeToken.type)
    }

    private fun <T> guardarArchivo(fileName: String, data: List<T>) {
        val json = gson.toJson(data)
        File(fileName).writeText(json)
    }
}
