package com.example.joseguzmanochoaexamenb1.models

data class Computador(
    val modelo: String,
    val precio: Double,
    val tipo: TipoComputador,
    val añoLanzamiento: Int,
    val enProduccion: Boolean,
    val nombreMarca: String
)

enum class TipoComputador {
    PORTATIL, ESCRITORIO
}