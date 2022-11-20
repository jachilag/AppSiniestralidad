package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class perfilFragment : Fragment() {
    lateinit var spinner_ciudad: Spinner;
//    var ciudades = MutableList<String>();
    lateinit var btn_actualizar: Button
    lateinit var databaseReference: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        databaseReference= Firebase.database.reference
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn_actualizar = view.findViewById<Button>(R.id.btn_actualizar)
        btn_actualizar.setOnClickListener {
            createUser()
            findNavController().navigate(R.id.action_perfilFragment_to_menuFragment)
        }
    }

    fun createUser(){

    }


}