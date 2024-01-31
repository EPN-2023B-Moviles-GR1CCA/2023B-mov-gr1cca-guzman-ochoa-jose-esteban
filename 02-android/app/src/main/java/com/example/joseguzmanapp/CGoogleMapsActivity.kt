package com.example.joseguzmanapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

class CGoogleMapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cgoogle_maps)
    }

    fun iniciarLogicaMapa() {
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            with(googleMap) {
                mapa = googleMap
                establecerConfiguracionMapa(googleMap)
            }
    }}

    fun establecerConfiguracionMapa(mapa: GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun moverQuicentro() {
        val quicentro = LatLng(-0.1761, -78.4804)
        val zoom = 17f
        val titulo = "Quicentro"
        val markQuicentro = MarkerOptions().position(quicentro).title(titulo)
        mapa.addMarker(markQuicentro)
    }

    fun agregarMarcador(lating: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions()
                .position(lating)
                .title(title)
        )
    }

    fun moverCamaraConZoom(lating: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(lating, zoom)
        )
    }

    fun anadirPolilinea() {
        with(mapa) {
            val poliLineaUno = mapa
                .addPolyline(
                    PolylineOptions()
                        .clickable(true)
                        .add(
                            LatLng(-0.1761, -78.4804),
                            LatLng(-0.1761, -78.4804),
                            LatLng(-0.1761, -78.4804),
                            LatLng)
                )
        }
    }

    fun anadirPoligono() {
        with (mapa)  {
            val poligonoUno = addPolygon(
                PolygonOptions()
                    .clickable(true)
                    .add(
                        LatLng(-0.1761, -78.4804),
                        LatLng(-0.1761, -78.4804),
                        LatLng(-0.1761, -78.4804),
                        LatLng(-0.1761, -78.4804)
                    )
            )
            poligonoUno.fillColor = -0xc771c4
            poligonoUno.tag = "Poligono Uno"
        }
    }

    fun escucharListeners() {
        mapa.setOnPolygonClickListener {
            log.i("google-maps", "setOnPolygonClickListener ${it.tag}")
            it.tag
        }
        mapa.setOnPolylineClickListener {
            log.i("google-maps", "setOnPolylineClickListener ${it.tag}")
            it.tag
        }
        mapa.setOnMarkerClickListener {
            log.i("google-maps", "setOnMarkerClickListener ${it.title}")
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveStartedListener {
            log.i("google-maps", "setOnCameraMoveStartedListener")
        }
        mapa.setOnCameraMoveListener {
            log.i("google-maps", "setOnCameraMoveListener")
        }
    }

    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val nombrePermiso = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisosFineLocation = ContextCompat.checkSelfPermission(
            contexto,
            nombrePermiso
        )
        val tienePermisos = permisosFineLocation == android.content.pm.PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    nombrePermiso,
                    nombrePermisoCoarse
                ),
                1
        }
    }
}