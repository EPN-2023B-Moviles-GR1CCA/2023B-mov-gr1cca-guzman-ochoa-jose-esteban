package com.example.joseguzmanapp

class BEntrenador(
    var id: Int,
    var nombre: String?,
    var descripcion: String?
) {
    override fun toString(): String {
        return "Entrenador(id=$id, nombre=$nombre, descripcion=$descripcion)"
    }
}