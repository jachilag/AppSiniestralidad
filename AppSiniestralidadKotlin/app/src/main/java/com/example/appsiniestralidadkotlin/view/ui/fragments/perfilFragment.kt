package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class perfilFragment : Fragment(), View.OnClickListener {
    lateinit var spinner_ciudad: Spinner;
//    var ciudades = MutableList<String>();
    lateinit var btn_actualizar: Button
    lateinit var databaseReference: DatabaseReference
    val database:DatabaseReference= FirebaseDatabase.getInstance().getReference("users")
    lateinit var fechaNacimiento:EditText
    lateinit var name:EditText
    lateinit var apellido:EditText
    lateinit var celular:EditText
    lateinit var ciudad:Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(com.example.appsiniestralidadkotlin.R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn_actualizar = view.findViewById<Button>(com.example.appsiniestralidadkotlin.R.id.btn_actualizar)
        btn_actualizar.setOnClickListener {
            createUser()
            findNavController().navigate(com.example.appsiniestralidadkotlin.R.id.action_perfilFragment_to_menuFragment)
        }
        //--------------------------------------------------------------------------
        // seccion de lectura de los datos del usuario


        name = view.findViewById<EditText>(com.example.appsiniestralidadkotlin.R.id.registro_nombre)
        apellido = view.findViewById<EditText>(com.example.appsiniestralidadkotlin.R.id.registro_apellido)
        celular = view.findViewById<EditText>(com.example.appsiniestralidadkotlin.R.id.registrar_celular)
        fechaNacimiento = view.findViewById<EditText>(com.example.appsiniestralidadkotlin.R.id.register_birthday)
        ciudad = view.findViewById<Spinner>(com.example.appsiniestralidadkotlin.R.id.spinner_ciudad)

        fechaNacimiento.setOnClickListener(this)

//        database.child(user?.uid.toString()).addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                name.setText(snapshot.child("name").value.toString())
//                birth.setText(snapshot.child("birthDate").value.toString())
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
    }

    fun createUser(){

    }
    
    override fun onClick(v: View?) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val recogerFecha = DatePickerDialog(requireContext(),{ view, year, month, day ->
            val mes = month + 1
            val dia = if (day < 10) "0$day" else day.toString()
            val mesFormateado = if (month < 10) "0$mes" else mes.toString()
            fechaNacimiento.setText("$dia/$mesFormateado/$year")
        }, year, month, day)
        recogerFecha.show()
    }


}