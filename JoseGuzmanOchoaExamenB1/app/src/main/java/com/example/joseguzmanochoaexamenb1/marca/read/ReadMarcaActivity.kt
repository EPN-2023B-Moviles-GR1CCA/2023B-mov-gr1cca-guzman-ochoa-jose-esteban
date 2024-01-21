package com.example.joseguzmanochoaexamenb1.marca.read

import com.example.joseguzmanochoaexamenb1.service.CRUDService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.joseguzmanochoaexamenb1.R

class ReadMarcaActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var marcasReadAdapter: MarcasReadAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marca_read_activity)

        recyclerView = findViewById(R.id.marcasRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val crudService = CRUDService(this)
        val marcas = crudService.leerMarcas()

        marcasReadAdapter = MarcasReadAdapter(marcas)
        recyclerView.adapter = marcasReadAdapter
    }
}