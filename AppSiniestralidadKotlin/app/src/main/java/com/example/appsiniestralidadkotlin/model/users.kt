package com.example.appsiniestralidadkotlin.model

import java.sql.Timestamp

data class users (
    var Apellidos: String?,
    var Nombres: String?,
    var Celular: String?,
    var Correo: String?,
    var fechaNacimiento: Timestamp?,
    var Ciudad: String?,
    var Urlfoto:String?,
    var Registro:String?
)