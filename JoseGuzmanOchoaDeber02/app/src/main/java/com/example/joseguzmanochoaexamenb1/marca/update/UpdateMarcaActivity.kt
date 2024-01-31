package com.example.joseguzmanochoaexamenb1.marca.update

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.joseguzmanochoaexamenb1.R
import com.example.joseguzmanochoaexamenb1.service.CRUDService

class UpdateMarcaActivity : ComponentActivity() {

    private lateinit var listView: ListView
    private val crudService = CRUDService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marca_update_activity)

        listView = findViewById(R.id.listViewMarcas)
        mostrarMarcas()
    }

    private fun mostrarMarcas() {
        val marcas = crudService.leerMarcas()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, marcas.map { it.nombre })
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, UpdateSingleMarcaActivity::class.java)
            intent.putExtra("nombreMarca", marcas[position].nombre)
            startActivity(intent)
        }
    }

}
