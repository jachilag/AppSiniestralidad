package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class DetalleMapFragment  : Fragment() {
    lateinit var reportero:String
    lateinit var fecha:String
    lateinit var involucrados:String
    lateinit var reporte: String
    lateinit var url: String
    lateinit var imgDetalle:ImageView
    lateinit var btn_salir: Button
    lateinit var tviewReportero: TextView
    lateinit var tviewReporte: TextView
    lateinit var tviewInvolucrados: TextView
    lateinit var tviewFecha: TextView
    lateinit var idDoc:String
    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_detalle_mapa, container, false)
        imgDetalle = view.findViewById(R.id.imgDetalle)
        btn_salir = view.findViewById(R.id.btn_salir_map)
        tviewReportero = view.findViewById(R.id.txtDetalleReporteroMap)
        tviewReporte = view.findViewById(R.id.txtDetalleReporteMap)
        tviewInvolucrados = view.findViewById(R.id.txtDetalleInvolucradosMap)
        tviewFecha = view.findViewById(R.id.txtDetalleFechaMap)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idDoc = arguments?.getString("idDoc").toString()
        db.collection("siniestros").document(idDoc).get().addOnSuccessListener {
            tviewFecha.setText(it.get("fecha") as String)
            tviewReporte.setText(it.get("reporte") as String)
            tviewReportero.setText(it.get("reportero") as String)
            tviewInvolucrados.setText(it.get("tipo") as String)
            url = it.get("url") as String
            Picasso.get().load(generateUrl(url)).into(imgDetalle)
        }


        btn_salir.setOnClickListener {
            findNavController().navigate(R.id.action_detalleMapFragment_to_configuracionesFragment)
        }
    }

    fun generateUrl(s: String): String? {
        var salida = s
        if(s.contains("drive")){
            val p = s.split("/").toTypedArray()
            salida = "https://drive.google.com/uc?export=download&id=" + p[5]
        }
        return salida
    }
}







//        reportero = arguments?.getString("reportero").toString()
//        reporte = arguments?.getString("reporte").toString()
//        involucrados = arguments?.getString("involucrados").toString()
//        fecha = arguments?.getString("fecha").toString()
//        txtLatitud = arguments?.getString("latitud").toString()
//        txtLongitud = arguments?.getString("longitud").toString()