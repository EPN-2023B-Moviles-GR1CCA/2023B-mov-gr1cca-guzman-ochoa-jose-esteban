package com.example.joseguzmanochoaexamenb1.computadoras.create

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.joseguzmanochoaexamenb1.R
import com.example.joseguzmanochoaexamenb1.models.Computador
import com.example.joseguzmanochoaexamenb1.models.MarcaComputador
import com.example.joseguzmanochoaexamenb1.models.TipoComputador
import com.example.joseguzmanochoaexamenb1.service.CRUDService

class AddComputadoraActivity : ComponentActivity() {
    private lateinit var spinnerMarcas: Spinner
    private lateinit var editTextModelo: EditText
    private lateinit var editTextPrecio: EditText
    private lateinit var spinnerTipo: Spinner
    private lateinit var editTextAnoLanzamiento: EditText
    private lateinit var checkBoxEnProduccion: CheckBox
    private lateinit var botonAgregar: Button

    private lateinit var crudService: CRUDService
    private var marcas: List<MarcaComputador> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.computadoras_create_activity)

        spinnerMarcas = findViewById(R.id.spinnerMarcas)
        editTextModelo = findViewById(R.id.editTextModelo)
        editTextPrecio = findViewById(R.id.editTextPrecio)
        spinnerTipo = findViewById(R.id.spinnerTipo)
        editTextAnoLanzamiento = findViewById(R.id.editTextAñoLanzamiento)
        checkBoxEnProduccion = findViewById(R.id.checkBoxEnProduccion)
        botonAgregar = findViewById(R.id.buttonAgregarComputadora)

        crudService = CRUDService(this)

        configurarSpinnerTipo()
        cargarMarcas()
        configurarSpinnerMarcas()
    }

    private fun cargarMarcas() {
        marcas = crudService.leerMarcas()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, marcas.map { it.nombre })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMarcas.adapter = adapter
    }

    private fun configurarSpinnerMarcas() {
        spinnerMarcas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun configurarSpinnerTipo() {
        val tipos = TipoComputador.values().map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipo.adapter = adapter
    }

    fun onAgregarComputadoraClick(view: View) {
        val marcaSeleccionada = marcas[spinnerMarcas.selectedItemPosition]
        val modelo = editTextModelo.text.toString()
        val precio = editTextPrecio.text.toString().toDoubleOrNull() ?: 0.0
        val tipo = TipoComputador.valueOf(spinnerTipo.selectedItem.toString())
        val anoLanzamiento = editTextAnoLanzamiento.text.toString().toIntOrNull() ?: 2024
        val enProduccion = checkBoxEnProduccion.isChecked

        val computador = Computador(modelo, precio, tipo, anoLanzamiento, enProduccion, marcaSeleccionada.nombre)
        marcaSeleccionada.agregarComputador(computador)
        crudService.actualizarMarca(marcaSeleccionada.nombre, marcaSeleccionada)
        crudService.addComputador(computador)

        Toast.makeText(this, "Computadora agregada a la marca ${marcaSeleccionada.nombre}", Toast.LENGTH_SHORT).show()
    }
}