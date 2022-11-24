package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R


class emergenciasFragment : Fragment() {
    lateinit var btn_salir: Button
    lateinit var cvPoliciaNacinal: CardView
    lateinit var cvLineaemergencia: CardView
    lateinit var cvCruzroja: CardView
    lateinit var cvBomberos: CardView
    lateinit var cvDefensaCivil: CardView
    lateinit var cvEmergenciasMedicas: CardView
    lateinit var cvPoliciaTransito: CardView
    lateinit var cvCentroToxicologico: CardView
    lateinit var cvGasNatural: CardView
    lateinit var cvAcueducto: CardView
    lateinit var cvCodensa: CardView
    lateinit var numPolicia: TextView
    lateinit var numEmergencia123: TextView
    lateinit var numCruzRoja: TextView
    lateinit var numBomberos: TextView
    lateinit var numDefensaCivil: TextView
    lateinit var numEmergenciasMedicas: TextView
    lateinit var numPoliciaTransito: TextView
    lateinit var numToxicologico: TextView
    lateinit var numGasNatural: TextView
    lateinit var numAcueducto: TextView
    lateinit var numCodensa: TextView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =inflater.inflate(R.layout.fragment_emergencias, container, false)
        cvPoliciaNacinal = view.findViewById(R.id.cvPoliciaNacinal)
        cvLineaemergencia = view.findViewById(R.id.cvLineaemergencia)
        cvCruzroja = view.findViewById(R.id.cvCruzroja)
        cvBomberos = view.findViewById(R.id.cvBomberos)
        cvDefensaCivil = view.findViewById(R.id.cvDefensaCivil)
        cvEmergenciasMedicas = view.findViewById(R.id.cvEmergenciasMedicas)
        cvPoliciaTransito = view.findViewById(R.id.cvPoliciaTransito)
        cvCentroToxicologico = view.findViewById(R.id.cvCentroToxicologico)
        cvGasNatural = view.findViewById(R.id.cvGasNatural)
        cvAcueducto = view.findViewById(R.id.cvAcueducto)
        cvCodensa = view.findViewById(R.id.cvCodensa)
        numPolicia = view.findViewById(R.id.numPolicia)
        numEmergencia123 = view.findViewById(R.id.numEmergencia123)
        numCruzRoja = view.findViewById(R.id.numCruzRoja)
        numBomberos = view.findViewById(R.id.numBomberos)
        numDefensaCivil = view.findViewById(R.id.numDefensaCivil)
        numEmergenciasMedicas = view.findViewById(R.id.numEmergenciasMedicas)
        numPoliciaTransito = view.findViewById(R.id.numPoliciaTransito)
        numToxicologico = view.findViewById(R.id.numToxicologico)
        numGasNatural = view.findViewById(R.id.numGasNatural)
        numAcueducto = view.findViewById(R.id.numAcueducto)
        numCodensa = view.findViewById(R.id.numCodensa)
        btn_salir = view.findViewById(R.id.salir_telefono)

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvPoliciaNacinal.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numPolicia.text}")
            }
            startActivity(intent)
        }
        cvLineaemergencia.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numEmergencia123.text}")
            }
            startActivity(intent)
        }
        cvCruzroja.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numCruzRoja.text}")
            }
            startActivity(intent)
        }
        cvBomberos.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numBomberos.text}")
            }
            startActivity(intent)
        }
        cvDefensaCivil.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numDefensaCivil.text}")
            }
            startActivity(intent)
        }
        cvEmergenciasMedicas.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numEmergenciasMedicas.text}")
            }
            startActivity(intent)
        }
        cvPoliciaTransito.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numPoliciaTransito.text}")
            }
            startActivity(intent)
        }
        cvCentroToxicologico.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numToxicologico.text}")
            }
            startActivity(intent)
        }
        cvGasNatural.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numGasNatural.text}")
            }
            startActivity(intent)
        }
        cvAcueducto.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numAcueducto.text}")
            }
            startActivity(intent)
        }
        cvCodensa.setOnClickListener{
            val intent =Intent(Intent.ACTION_DIAL).apply{
                data = Uri.parse("tel:${numCodensa.text}")
            }
            startActivity(intent)
        }

        btn_salir.setOnClickListener {
            findNavController().navigate(R.id.action_emergenciasFragment_to_menuFragment)
        }
    }
}