package com.example.joseguzmanapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        mostrarSnackBar(nombre!!, apellido!!, edad)
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton.setOnClickListener {
            devolverRespuesta()
        }
    }
}