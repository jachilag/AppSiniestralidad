package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class perfilFragment : Fragment(), View.OnClickListener {
    lateinit var spinner_ciudad: Spinner;
//    var ciudades = MutableList<String>();
    lateinit var btn_actualizar: Button
    var databaseReference: DatabaseReference = Firebase.database.reference.child("users")
    private val database:DatabaseReference= FirebaseDatabase.getInstance().getReference("users")
    private lateinit var correo:EditText
    lateinit var fechaNacimiento:EditText
    lateinit var name:EditText
    lateinit var apellido:EditText
    lateinit var celular:EditText
    lateinit var ciudad:Spinner
    var firebaseAuth: FirebaseAuth = Firebase.auth
    val user = firebaseAuth.currentUser

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //--------------------------------------------------------------------------
        // seccion de lectura de los datos del usuario
        correo = view.findViewById(R.id.registro_correo)
        name = view.findViewById(R.id.registro_nombre)
        apellido = view.findViewById(R.id.registro_apellido)
        celular = view.findViewById(R.id.registrar_celular)
        fechaNacimiento = view.findViewById(R.id.register_birthday)
        ciudad = view.findViewById(R.id.spinner_ciudad)
        fechaNacimiento.setOnClickListener(this)


        // fijamos los campos edittext con los valores encontrados en firebase asociados a dicho usuario
        correo.setText(user?.email.toString())

        //realtime database
        database.child(user?.uid.toString()).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                name.setText(snapshot.child("Nombres").value.toString())
                fechaNacimiento.setText(snapshot.child("fechaNacimiento").value.toString())
                apellido.setText(snapshot.child("Apellidos").value.toString())
                celular.setText(snapshot.child("Celular").value.toString())
        //                ciudad.setText(snapshot.child("Ciudad").value.toString())
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        btn_actualizar = view.findViewById(R.id.btn_actualizar)
        btn_actualizar.setOnClickListener {
            updateUser(name.text.toString(),apellido.text.toString(),celular.text.toString(),
                fechaNacimiento.text.toString(),"bogota")
            findNavController().navigate(R.id.action_perfilFragment_to_menuFragment)
            Toast.makeText(requireContext(),"Datos Actualizados", Toast.LENGTH_LONG).show()
        }

        correo.setOnClickListener{
            Toast.makeText(it.context,"No se puede editar CORREO", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUser(
        nombre:String, apellidos:String,
        celular: String, nacimiento:String, ciudad:String) {
        Log.d("Nombres: ", nombre)
        val userdb = databaseReference.child(user?.uid.toString())
        userdb.child("Nombres").setValue(nombre)
        userdb.child("Apellidos").setValue(apellidos)
        userdb.child("Celular").setValue(celular)
        userdb.child("fechaNacimiento").setValue(nacimiento)
        //userdb.child("Ciudad").setValue(ciudad)
    }

    override fun onClick(v: View?) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val recogerFecha = DatePickerDialog(requireContext(), R.style.DatePickerTheme,{ view, year, month, day ->
            val mes = month + 1
            val dia = if (day < 10) "0$day" else day.toString()
            val mesFormateado = if (month < 10) "0$mes" else mes.toString()
            fechaNacimiento.setText("$dia/$mesFormateado/$year")
        }, year, month, day)
        recogerFecha.datePicker.maxDate = c.timeInMillis
        recogerFecha.show()
    }


    /*database.addValueEventListener(object :ValueEventListener{
      override fun onDataChange(snapshot: DataSnapshot) {
          for (ds in snapshot.children){
              if(ds.equals(user?.uid.toString())){
                  name.setText(ds.child("name").value.toString())
                  birth.setText(ds.child("birthDate").value.toString())
              }
          }
      }

      override fun onCancelled(error: DatabaseError) {

      }
    })*/


}