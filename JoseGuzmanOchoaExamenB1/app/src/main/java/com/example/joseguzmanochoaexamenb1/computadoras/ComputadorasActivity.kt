package com.example.joseguzmanochoaexamenb1.computadoras

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
import com.example.joseguzmanochoaexamenb1.computadoras.create.AddComputadoraActivity
import com.example.joseguzmanochoaexamenb1.computadoras.delete.DeleteComputadoraActivity
import com.example.joseguzmanochoaexamenb1.computadoras.read.ReadComputadorasActivity
import com.example.joseguzmanochoaexamenb1.computadoras.ui.theme.JoseGuzmanOchoaExamenB1Theme
import com.example.joseguzmanochoaexamenb1.computadoras.update.UpdateComputadorasActivity
import com.example.joseguzmanochoaexamenb1.marca.create.AddMarcaActivity
import com.example.joseguzmanochoaexamenb1.marca.delete.DeleteMarcaActivity
import com.example.joseguzmanochoaexamenb1.marca.read.ReadMarcaActivity
import com.example.joseguzmanochoaexamenb1.marca.update.UpdateMarcaActivity

class ComputadorasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.computadoras_activity)

        val btnAddComputadora = findViewById<Button>(R.id.btnAddComputadora)
        val btnReadComputadora = findViewById<Button>(R.id.btnReadComputadora)
        val btnDeleteComputadora = findViewById<Button>(R.id.btnDeleteComputadora)
        val btnUpdateComputadora = findViewById<Button>(R.id.btnUpdateComputadora)

        btnAddComputadora.setOnClickListener {
            val intent = Intent(this, AddComputadoraActivity::class.java)
            startActivity(intent)
        }
        btnReadComputadora.setOnClickListener {
            val intent = Intent(this, ReadComputadorasActivity::class.java)
            startActivity(intent)
        }
        btnDeleteComputadora.setOnClickListener {
            val intent = Intent(this, DeleteComputadoraActivity::class.java)
            startActivity(intent)
        }
        btnUpdateComputadora.setOnClickListener {
            val intent = Intent(this, UpdateComputadorasActivity::class.java)
            startActivity(intent)
        }
    }
}