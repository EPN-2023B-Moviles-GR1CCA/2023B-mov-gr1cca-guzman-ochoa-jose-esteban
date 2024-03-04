package com.example.joseguzmanochoaexamenb1.service

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.joseguzmanochoaexamenb1.models.Computador
import com.example.joseguzmanochoaexamenb1.models.MarcaComputador
import com.example.joseguzmanochoaexamenb1.models.TipoComputador
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

class CRUDService(private val context: Context) {
    private val db: FirebaseFirestore = Firebase.firestore

    fun addMarca(marca: MarcaComputador) {
        db.collection("marcas").add(marca)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun leerMarcas(): List<MarcaComputador> {
        return try {
            db.collection("marcas").get().await().documents.mapNotNull { snapshot ->
                snapshot.data?.let { data ->
                    val fechaMap = data["fechaRegistro"] as? Map<String, Int>
                    val fechaRegistro = fechaMap?.let {
                        LocalDate.of(it["year"]!!, it["monthValue"]!!, it["dayOfMonth"]!!)
                    }
                    val añoFundacion = (data["añoFundacion"]?.toString()?.toIntOrNull()) ?: 0

                    MarcaComputador(
                        nombre = data["nombre"] as? String ?: "",
                        paisOrigen = data["paisOrigen"] as? String ?: "",
                        añoFundacion = añoFundacion,
                        esIndependiente = data["esIndependiente"] as? Boolean ?: false,
                        fechaRegistro = fechaRegistro ?: LocalDate.now(),
                        computadores = mutableListOf()
                    )
                }
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    fun actualizarMarca(nombre: String, nuevaMarca: MarcaComputador) {
        db.collection("marcas").whereEqualTo("nombre", nombre).get().addOnSuccessListener { documents ->
            for (document in documents) {
                db.collection("marcas").document(document.id).set(nuevaMarca)
            }
        }
    }

    fun eliminarMarca(nombre: String) {
        db.collection("marcas").whereEqualTo("nombre", nombre).get().addOnSuccessListener { documents ->
            for (document in documents) {
                db.collection("marcas").document(document.id).delete()
            }
        }
    }

    fun addComputador(computador: Computador) {
        db.collection("computadores").add(computador)
    }

    suspend fun leerComputadores(): List<Computador> {
        return try {
            db.collection("computadores").get().await().documents.mapNotNull { snapshot ->
                snapshot.data?.let { data ->
                    val modelo = data["modelo"] as? String ?: ""
                    val precio = (data["precio"] as? Number)?.toDouble() ?: 0.0
                    val tipo = TipoComputador.valueOf((data["tipo"] as? String) ?: TipoComputador.ESCRITORIO.name)
                    val añoLanzamiento = (data["añoLanzamiento"]?.toString()?.toIntOrNull()) ?: 0
                    val enProduccion = data["enProduccion"] as? Boolean ?: false
                    val nombreMarca = data["nombreMarca"] as? String ?: ""

                    Computador(modelo, precio, tipo, añoLanzamiento, enProduccion, nombreMarca)
                }
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    fun actualizarComputador(modelo: String, nuevoComputador: Computador) {
        db.collection("computadores").whereEqualTo("modelo", modelo).get().addOnSuccessListener { documents ->
            for (document in documents) {
                db.collection("computadores").document(document.id).set(nuevoComputador)
            }
        }
    }

    fun eliminarComputador(modelo: String) {
        db.collection("computadores").whereEqualTo("modelo", modelo).get().addOnSuccessListener { documents ->
            for (document in documents) {
                db.collection("computadores").document(document.id).delete()
            }
        }
    }
}
