package com.example.appsiniestralidadkotlin.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appsiniestralidadkotlin.model.ciudades
import com.example.appsiniestralidadkotlin.model.siniestros
import com.example.appsiniestralidadkotlin.model.users
import com.google.firebase.firestore.FirebaseFirestore

class Repository {
    fun getSiniestrosData(): LiveData<MutableList<siniestros>> {
        val mutableLiveData = MutableLiveData<MutableList<siniestros>>()
        FirebaseFirestore.getInstance().collection("siniestros").get().addOnSuccessListener(){
            result->
            val listData = mutableListOf<siniestros>()
            for(document in result){
//                val asistenciaMedica = document.getString("asistenciaMedica")
                val fecha = document.getString("fecha")
                val reportero = document.getString("reportero")
                val reporte = document.getString("reporte")
                val tipo = document.getString("tipo")
                val ubicacion = document.getString("ubicacion")
                val url = generateUrl(document.getString("url").toString())
                val siniestro = siniestros(asistenciaMedica = null,fecha = fecha, reportero = reportero, reporte = reporte,tipo =tipo, ubicacion = ubicacion, url = url)
                listData.add(siniestro)
            }
            mutableLiveData.value=listData
        }
        return  mutableLiveData
    }

    fun generateUrl(s: String): String? {
        val p = s.split("/").toTypedArray()
        return "https://drive.google.com/uc?export=download&id=" + p[5]
    }

    fun getUsersData(): LiveData<MutableList<users>> {
        val mutableLiveData = MutableLiveData<MutableList<users>>()
        FirebaseFirestore.getInstance().collection("users").get().addOnSuccessListener(){
                result->
            val listData = mutableListOf<users>()
            for(document in result){
                val nombres = document.getString("asistenciaMedica")
                val apellidos = document.getString("fecha")
                val correo = document.getString("reportero")
                val celular = document.getString("tipo")
                val fechaNacimiento = document.getString("ubicacion")
                val user = users(Nombres = nombres, Apellidos = apellidos, Celular = celular, Correo = correo, fechaNacimiento = fechaNacimiento)
                listData.add(user)
            }
            mutableLiveData.value=listData
        }
        return  mutableLiveData
    }


    fun getCiudadesData(): LiveData<MutableList<ciudades>> {
        val mutableLiveData = MutableLiveData<MutableList<ciudades>>()
        FirebaseFirestore.getInstance().collection("ciudades").get().addOnSuccessListener(){
                result->
            val listData = mutableListOf<ciudades>()
            for(document in result){
                val ciudad = ciudades(nombre = document.getString("ciudad"))
                listData.add(ciudad)
            }
            mutableLiveData.value = listData
        }
        return  mutableLiveData
    }
}


