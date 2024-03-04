package com.example.joseguzmanochoaexamenb1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.joseguzmanochoaexamenb1.computadoras.ComputadorasActivity
import com.example.joseguzmanochoaexamenb1.marca.MarcaActivity
import com.example.joseguzmanochoaexamenb1.marca.create.AddMarcaActivity
import com.example.joseguzmanochoaexamenb1.marca.delete.DeleteMarcaActivity
import com.example.joseguzmanochoaexamenb1.marca.read.ReadMarcaActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMarcas = findViewById<Button>(R.id.btnMarcas)
        val btnComputadoras = findViewById<Button>(R.id.btnComputadoras)

        btnMarcas.setOnClickListener {
            val intent = Intent(this, MarcaActivity::class.java)
            startActivity(intent)
        }
        btnComputadoras.setOnClickListener {
            val intent = Intent(this, ComputadorasActivity::class.java)
            startActivity(intent)
        }

    }
}