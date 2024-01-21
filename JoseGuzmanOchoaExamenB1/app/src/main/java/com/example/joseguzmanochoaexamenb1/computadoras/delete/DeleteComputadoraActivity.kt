package com.example.joseguzmanochoaexamenb1.computadoras.delete

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.joseguzmanochoaexamenb1.R
import com.example.joseguzmanochoaexamenb1.computadoras.delete.ui.theme.JoseGuzmanOchoaExamenB1Theme
import com.example.joseguzmanochoaexamenb1.models.Computador
import com.example.joseguzmanochoaexamenb1.service.CRUDService

class DeleteComputadoraActivity : ComponentActivity() {
    private lateinit var spinnerComputadoras: Spinner
    private lateinit var botonEliminar: Button
    private lateinit var crudService: CRUDService
    private var computadoras: List<Computador> = listOf()
    private var computadoraSeleccionada: Computador? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.computadoras_delete_activity)

        spinnerComputadoras = findViewById(R.id.spinnerComputadoras)
        botonEliminar = findViewById(R.id.botonEliminar)
        crudService = CRUDService(this)

        cargarComputadoras()
        configurarSpinnerComputadoras()
    }

    private fun cargarComputadoras() {
        computadoras = crudService.leerComputadores()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, computadoras.map { it.modelo })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerComputadoras.adapter = adapter
    }

    private fun configurarSpinnerComputadoras() {
        spinnerComputadoras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                computadoraSeleccionada = computadoras[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                computadoraSeleccionada = null
            }
        }
    }

    fun onEliminarComputadoraClick(view: View) {
        computadoraSeleccionada?.let {
            try {
                crudService.eliminarComputador(it.modelo)
                Toast.makeText(this, "Computadora eliminada con éxito", Toast.LENGTH_SHORT).show()
                cargarComputadoras()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al eliminar la computadora", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Por favor, seleccione una computadora", Toast.LENGTH_SHORT).show()
    }

}