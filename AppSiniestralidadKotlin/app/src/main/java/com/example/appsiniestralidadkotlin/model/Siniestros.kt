package com.example.appsiniestralidadkotlin.model

import java.sql.Timestamp

data class Siniestros (
    var asistenciaMedica: Boolean? ,
    var reportero: String?,
    var fecha: Timestamp?,
    var Latitud: Double? ,
    var Longitud: Double?,
)