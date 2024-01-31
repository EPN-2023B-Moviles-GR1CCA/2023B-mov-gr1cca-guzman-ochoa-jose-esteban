package com.example.joseguzmanochoaexamenb1.marca.update

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.joseguzmanochoaexamenb1.R
import com.example.joseguzmanochoaexamenb1.marca.update.ui.theme.JoseGuzmanOchoaExamenB1Theme
import com.example.joseguzmanochoaexamenb1.models.MarcaComputador
import com.example.joseguzmanochoaexamenb1.service.CRUDService
import java.time.LocalDate

class UpdateSingleMarcaActivity : ComponentActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextPaisOrigen: EditText
    private lateinit var buttonActualizar: Button
    private lateinit var anoFundacion: EditText
    private val crudService = CRUDService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marca_update_single)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextPaisOrigen = findViewById(R.id.editTextPaisOrigen)
        anoFundacion = findViewById(R.id.editTextFundacion)
        buttonActualizar = findViewById(R.id.buttonActualizar)

        val nombreMarca = intent.getStringExtra("nombreMarca")
        cargarDatosMarca(nombreMarca)

        buttonActualizar.setOnClickListener {
            val nuevaMarca = MarcaComputador(
                nombre = editTextNombre.text.toString(),
                paisOrigen = editTextPaisOrigen.text.toString(),
                añoFundacion = anoFundacion.text.toString().toIntOrNull() ?: 0,
                esIndependiente = true,
                fechaRegistro = LocalDate.now(),
            )
            crudService.actualizarMarca(nombreMarca ?: "", nuevaMarca)
            finish()
        }
    }

    private fun cargarDatosMarca(nombreMarca: String?) {
        nombreMarca?.let {
            val marca = crudService.leerMarcas().find { it.nombre == nombreMarca }
            marca?.let {
                editTextNombre.setText(marca.nombre)
                editTextPaisOrigen.setText(marca.paisOrigen)
                anoFundacion.setText(marca.añoFundacion.toString())
            }
        }
    }
}