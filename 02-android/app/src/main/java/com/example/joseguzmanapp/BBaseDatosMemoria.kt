package com.example.joseguzmanapp

class BBaseDatosMemoria {
    val arregloBEntrenador = arrayListOf<BEntrenador>()

    init {
        arregloBEntrenador
            .add(
                BEntrenador(
                    1,
                    "Jose",
                    "Guapo"
                )
            )
        arregloBEntrenador
            .add(
                BEntrenador(
                    2,
                    "Juan",
                    "Feo"
                )
            )
        arregloBEntrenador
            .add(
                BEntrenador(
                    3,
                    "Pedro",
                    "Alto"
                )
            )
    }
}