package com.example.joseguzmanapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val botonGoogleMaps = findViewById<Button>(R.id.btn_gmaps)
        botonGoogleMaps.setOnClickListener {
            irActividad(GoogleMapsInicio::class.java)
        }

    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}