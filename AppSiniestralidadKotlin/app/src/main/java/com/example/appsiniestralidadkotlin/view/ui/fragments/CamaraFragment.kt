package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R

class CamaraFragment: Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_camara, container, false)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val involucrados = arguments?.getString("involucrados")
        val latitud = arguments?.getString("latitud")
        val longitud = arguments?.getString("longitud")
        Toast.makeText(requireContext(), "$latitud,$longitud",Toast.LENGTH_LONG)
        Log.d("LATITUD Y LONGITUD:","$latitud,$longitud")
        val bundle = bundleOf(
            "involucrados" to involucrados,
            "latitud" to latitud,
            "longitud" to longitud,
        )

        val btn_continuar = view.findViewById<Button>(R.id.btn_avanzar_to_descripcion)
        btn_continuar.setOnClickListener {
            findNavController().navigate(R.id.action_camaraFragment_to_descripcionFragment,bundle)
        }
    }
}