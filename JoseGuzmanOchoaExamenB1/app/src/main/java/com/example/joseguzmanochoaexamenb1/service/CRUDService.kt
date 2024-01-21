package com.example.joseguzmanochoaexamenb1.service

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.joseguzmanochoaexamenb1.models.Computador
import com.example.joseguzmanochoaexamenb1.models.MarcaComputador
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.time.LocalDate

class CRUDService (private val context: Context) {
    private val marcasFileName = "marcas.json"
    private val computadoresFileName = "computadores.json"
    private val gson = Gson()

    fun addMarca(marca: MarcaComputador) {
        val marcas = leerMarcas().toMutableList()
        marcas.add(marca)
        println(marcas)
        guardarMarcas(marcas)
    }

    fun leerMarcas(): List<MarcaComputador> {
        val typeToken = object : TypeToken<List<MarcaComputador>>() {}
        return leerArchivo(marcasFileName, typeToken) ?: emptyList()
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

        println("guardarMarcas:")
        println(marcas)

        guardarArchivo(context, marcasFileName, marcas)
    }

    fun addComputador(computador: Computador) {
        val computadores = leerComputadores().toMutableList()
        computadores.add(computador)
        guardarComputadores(computadores)
    }

    fun leerComputadores(): List<Computador> {
        val typeToken = object : TypeToken<List<Computador>>() {}
        return leerArchivo(computadoresFileName, typeToken) ?: emptyList()
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
        guardarArchivo(context, computadoresFileName, computadores)
    }

    fun <T> leerArchivo(fileName: String, typeToken: TypeToken<List<T>>): List<T> {
        val file = File(context.filesDir, fileName)

        if (!file.exists() || file.readText().isEmpty()) return emptyList()

        val json = file.readText()
        return try {
            gson.fromJson(json, typeToken.type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun <T> guardarArchivo(context: Context, fileName: String, data: List<T>) {
        val file = File(context.filesDir, fileName)
        val json = gson.toJson(data)

        file.writeText(json)
    }
}