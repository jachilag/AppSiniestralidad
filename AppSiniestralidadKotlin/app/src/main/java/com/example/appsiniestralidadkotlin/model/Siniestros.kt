package com.example.appsiniestralidadkotlin.model

import java.io.Serializable
import java.sql.Timestamp

class Siniestros: Serializable {
    var asistenciaMedica: Boolean = false
    lateinit var reportero: String
    lateinit var fecha: Timestamp
    var Latitud: Double = 0.0
    var Longitud: Double = 0.0



}