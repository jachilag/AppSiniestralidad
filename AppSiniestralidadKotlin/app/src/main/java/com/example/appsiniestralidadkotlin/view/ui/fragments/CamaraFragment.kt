package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CamaraFragment: Fragment() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var email: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        firebaseAuth = Firebase.auth
        email = firebaseAuth.currentUser?.email.toString()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camara, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val btn_regresar = view.findViewById<Button>(R.id.btn_regresar_ubicacion)
//        btn_regresar.setOnClickListener {
//            findNavController().navigate(R.id.action_descripcionFragment_to_menuFragment)
//        }
        val btn_continuar = view.findViewById<Button>(R.id.btn_avanzar_to_descripcion)
        btn_continuar.setOnClickListener {
            findNavController().navigate(R.id.action_camaraFragment_to_descripcionFragment)
        }
    }
}