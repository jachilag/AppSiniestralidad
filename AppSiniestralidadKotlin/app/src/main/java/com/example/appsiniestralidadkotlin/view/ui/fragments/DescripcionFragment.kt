package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


class DescripcionFragment : Fragment() {

    var firebaseAuth: FirebaseAuth = Firebase.auth
    lateinit var idUser: String
    val db = FirebaseFirestore.getInstance()
    lateinit var cbAsistencia: CheckBox
    lateinit var txtDescripcion: TextInputEditText
    lateinit var btn_finalizar: Button
    lateinit var involucrados: String
    lateinit var latitud: String
    lateinit var longitud: String
    var asistencia by Delegates.notNull<Boolean>()
    var urlFoto: String = "https://drive.google.com/file/d/1alFVT-wYL2tlQttK8fQ2AlPeRUUjy6Pb/view?usp=share_link"
    lateinit var reporte:String
    lateinit var reportero:String

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_descripcion, container, false)
        txtDescripcion = view.findViewById(R.id.textDescripcion)
        cbAsistencia = view.findViewById(R.id.cbAsistencia)
        btn_finalizar = view.findViewById(R.id.btn_finalizar)
        idUser = firebaseAuth.currentUser?.uid.toString()
        db.collection("users").document(idUser).get().addOnSuccessListener {
            reportero = it.get("Nombres").toString() + " " + it.get("Apellidos").toString()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        involucrados = arguments?.getString("involucrados").toString()
        latitud = arguments?.getString("latitud").toString()
        longitud = arguments?.getString("longitud").toString()

        btn_finalizar.setOnClickListener {
            asistencia = cbAsistencia.isChecked
//            reporte = (txtDescripcion as EditText).text.toString()
            reporte = txtDescripcion.text.toString()
            updateSiniestro(asistencia,reporte,involucrados,latitud,longitud,urlFoto)
            findNavController().navigate(R.id.action_descripcionFragment_to_menuFragment)
        }
    }

    private fun updateSiniestro(
        asistencia: Boolean,
        reporte: String,
        involucrados: String,
        latitud: String,
        longitud: String,
        urlFoto: String
    ) {
        // se obtiene la hora y fecha del registro
        val dateFormat1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateFormat2 = SimpleDateFormat("yyyyMMddHHmmss")
        val fecha = dateFormat1.format(Date())
        val date = dateFormat2.format(Date())
        val documento = date+"_"+idUser
        db.collection("siniestros").document(documento).set(
            hashMapOf(
                "asistenciaMedica" to asistencia,
                "fecha" to fecha,
                "reporte" to reporte,
                "idReportero" to idUser,
                "reportero" to reportero,
                "tipo" to involucrados,
                "latitud" to latitud,
                "longitud" to longitud,
                "url" to urlFoto
            )
        )
    }


}