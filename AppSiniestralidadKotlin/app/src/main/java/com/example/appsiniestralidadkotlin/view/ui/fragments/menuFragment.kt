package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R
import android.widget.ImageView


class menuFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val cardPerfil = view.findViewById<CardView>(R.id.fragPerfil)
        cardPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_perfilFragment)
        }

        val cardReportar = view.findViewById<CardView>(R.id.fragReportar)
        cardPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_reportarFragment)
        }

        val cardNoticias = view.findViewById<CardView>(R.id.fragNoticias)
        cardPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_noticiasFragment)
        }

        val cardConfiguraciones = view.findViewById<CardView>(R.id.fragConfiguraciones)
        cardPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_configuracionesFragment)
        }

        val cardEmergencias = view.findViewById<CardView>(R.id.fragEmergencias)
        cardPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_emergenciasFragment)
        }

        val cardAyuda = view.findViewById<CardView>(R.id.fragAyuda)
        cardPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_ayudaFragment)
        }


    }
}