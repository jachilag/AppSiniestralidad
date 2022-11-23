package com.example.appsiniestralidadkotlin.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appsiniestralidadkotlin.model.Ciudad
import com.example.appsiniestralidadkotlin.model.siniestros
import com.google.firebase.firestore.FirebaseFirestore

class Repository {
    fun getSiniestrosData(): LiveData<MutableList<siniestros>> {
        val mutableLiveData = MutableLiveData<MutableList<siniestros>>()
        FirebaseFirestore.getInstance().collection("siniestros").get().addOnSuccessListener {
            result->
            val listData = mutableListOf<siniestros>()
            for(document in result){
//                val asistenciaMedica = document.getString("asistenciaMedica")
                val fecha = document.getString("fecha")
                val reportero = document.getString("reportero")
                val idReportero = document.getString("idReportero")
                val reporte = document.getString("reporte")
                val tipo = document.getString("tipo")
                val latitud = document.getString("longitud")
                val longitud = document.getString("latitud")
                val url = generateUrl(document.getString("url").toString())
                val siniestro = siniestros(
                    asistenciaMedica = null,
                    fecha = fecha,
                    reportero = reportero,
                    idReportero = idReportero,
                    reporte = reporte,
                    tipo =tipo,
                    latitud = latitud,
                    longitud = longitud,
                    url = url
                )
                listData.add(siniestro)
            }
            mutableLiveData.value=listData
        }
        return  mutableLiveData
    }

 //funcion encontrada en el siguiente enlace:
//https://medium.com/@josealbertoarangos/c%C3%B3mo-cargar-una-imagen-de-google-drive-en-un-imageview-en-android-c0818efa3c07

    fun generateUrl(s: String): String? {
        val p = s.split("/").toTypedArray()
        return "https://drive.google.com/uc?export=download&id=" + p[5]
    }


    fun getDataCiudades(): LiveData<MutableList<Ciudad>> {
        val mutableLiveData = MutableLiveData<MutableList<Ciudad>>()
        FirebaseFirestore.getInstance().collection("Ciudades").get().addOnSuccessListener { result->
            val listData = mutableListOf<Ciudad>()
            for(document in result){
                val nombre = document.getString("nombre")
                val ciudad = Ciudad(nombre=nombre)
                listData.add(ciudad)
            }
            mutableLiveData.value=listData
        }
        return  mutableLiveData
    }
}


