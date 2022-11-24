package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R
import java.text.SimpleDateFormat
import java.util.*


class reportarFragment : Fragment() {

    lateinit var cbPeaton:CheckBox
    lateinit var cbBiciusuario:CheckBox
    lateinit var cbMotociclista:CheckBox
    lateinit var cbVehiculoPrivado:CheckBox
    lateinit var cbVehiculoPublico:CheckBox
    lateinit var cbTransmilenio:CheckBox
    lateinit var cbVehiculoCarga:CheckBox
    lateinit var cbVehiculoAnimal:CheckBox
    lateinit var cbOtro:CheckBox
    lateinit var textOtro:EditText
    lateinit var involucrados:ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val date = dateFormat.format(Date())
        val view = inflater.inflate(R.layout.fragment_reportar, container, false)
        involucrados = ArrayList<String>()
        cbPeaton = view.findViewById(R.id.cbPeaton)
        cbBiciusuario = view.findViewById(R.id.cbBiciusuario)
        cbMotociclista = view.findViewById(R.id.cbMotociclista)
        cbVehiculoPrivado = view.findViewById(R.id.cbVehiculoPrivado)
        cbVehiculoPublico = view.findViewById(R.id.cbVehiculoPublico)
        cbTransmilenio = view.findViewById(R.id.cbTransmilenio)
        cbVehiculoCarga = view.findViewById(R.id.cbVehiculoCarga)
        cbVehiculoAnimal = view.findViewById(R.id.cbVehiculoAnimal)
        cbOtro = view.findViewById(R.id.cbOtro)
        textOtro = view.findViewById(R.id.textOtro)

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cbOtro.setOnClickListener {
            if (cbOtro.isChecked) {
                textOtro.visibility = View.VISIBLE
            } else {
                textOtro.visibility = View.INVISIBLE
            }
        }

        val btn_siguiente = view.findViewById<Button>(R.id.btn_avanzar_to_ubicacion)
        btn_siguiente.setOnClickListener {
            obtenerInvolucrados()
            val txtInvolucrados = textoInvolucrados()
            val bundle = bundleOf(
                "involucrados" to txtInvolucrados,
            )
            findNavController().navigate(R.id.action_reportarFragment_to_ubicacionFragment,bundle)
        }
    }

    fun obtenerInvolucrados() {
        if (cbPeaton.isChecked) {
            involucrados.add(cbPeaton.text.toString())
        }

        if (cbBiciusuario.isChecked) {
            involucrados.add(cbBiciusuario.text.toString())
        }

        if (cbMotociclista.isChecked) {
            involucrados.add(cbMotociclista.text.toString())
        }

        if (cbVehiculoPrivado.isChecked) {
            involucrados.add(cbVehiculoPrivado.text.toString())
        }

        if (cbVehiculoPublico.isChecked) {
            involucrados.add(cbVehiculoPublico.text.toString())
        }

        if (cbTransmilenio.isChecked) {
            involucrados.add(cbTransmilenio.text.toString())
        }

        if (cbVehiculoCarga.isChecked) {
            involucrados.add(cbVehiculoCarga.text.toString())
        }

        if (cbVehiculoAnimal.isChecked) {
            involucrados.add(cbVehiculoAnimal.text.toString())
        }

        if (cbOtro.isChecked) {
            involucrados.add("otros: "+textOtro.text.toString())
        }
    }

    fun textoInvolucrados():String{
        var salida:String = ""
        for(agente in involucrados){
            salida += "$agente, "
        }
        return salida
    }
}
