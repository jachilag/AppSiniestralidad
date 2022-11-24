package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appsiniestralidadkotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*


//--------------------------------------------------------------------------------------------
// CODIGO BASADO EN OBTENER Y ACTUALIZAR LOS DATOS CON FIRESTORE CLOUD
class perfilFragment : Fragment(), View.OnClickListener,AdapterView.OnItemSelectedListener {
    lateinit var btn_actualizar: Button
    lateinit var btn_cambiarFoto: Button
    private lateinit var correo:EditText
    lateinit var fechaNacimiento:EditText
    lateinit var name:EditText
    lateinit var apellido:EditText
    lateinit var celular:EditText
    lateinit var spCiudad:Spinner
    lateinit var city:EditText
    lateinit var fotoPerfil:ImageView
    lateinit var storageRef : StorageReference
    lateinit var urlFoto:String
    val db = FirebaseFirestore.getInstance()
    var firebaseAuth: FirebaseAuth = Firebase.auth
    val user = firebaseAuth.currentUser

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // asignamos view a variable
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)
        storageRef = FirebaseStorage.getInstance().reference

        //--------------------------------------------------------------------------
        // asignacion de los id de los layouts a variables kt
        fotoPerfil = view.findViewById(R.id.img_perfil_1)
        btn_actualizar = view.findViewById(R.id.btn_actualizar)
        btn_cambiarFoto = view.findViewById(R.id.btn_tomarFoto)
        correo = view.findViewById(R.id.registro_correo)
        name = view.findViewById(R.id.registro_nombre)
        apellido = view.findViewById(R.id.registro_apellido)
        celular = view.findViewById(R.id.registrar_celular)
        fechaNacimiento = view.findViewById(R.id.register_birthday)
        spCiudad = view.findViewById(R.id.spinner_ciudad)
        fechaNacimiento.setOnClickListener(this)
        city = view.findViewById(R.id.registro_ciudad)


        //llena el spinner con los valores del stringArray del archivo strings.xml. no se pudo hacer con firebase :(
        ArrayAdapter.createFromResource(requireContext(),R.array.ciudades,android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spCiudad.adapter = adapter
            }

        // Inflate the layout for this fragment
        return view
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fijamos los campos edittext con los valores encontrados en firebase asociados a dicho usuario
        correo.setText(user?.email.toString())
        db.collection("users").document(user?.uid.toString()).get().addOnSuccessListener {
            name.setText(it.get("Nombres") as String?)
            fechaNacimiento.setText(it.get("fechaNacimiento") as String?)
            apellido.setText(it.get("Apellidos") as String?)
            celular.setText(it.get("Celular") as String?)
            city.setText(it.get("Ciudad")as String?)
            urlFoto = it.get("UrlFoto") as String
            cargarFoto(requireContext(),fotoPerfil,urlFoto)
        }

        //listener del spinner para elegir cuidad
        spCiudad.onItemSelectedListener = this

        //listener para actualizar los datos en firebase
        btn_actualizar.setOnClickListener {
            updateUser(name.text.toString(),apellido.text.toString(),celular.text.toString(),
                fechaNacimiento.text.toString(),city.text.toString(), urlFoto,"")
            findNavController().navigate(R.id.action_perfilFragment_to_menuFragment)
            Toast.makeText(requireContext(),"Datos Actualizados", Toast.LENGTH_LONG).show()
        }

        btn_cambiarFoto.setOnClickListener{
            findNavController().navigate(R.id.action_perfilFragment_to_fotoPerfilFragment)
        }

        correo.setOnClickListener{
            Toast.makeText(it.context,"No se puede editar CORREO", Toast.LENGTH_LONG).show()
        }

        city.setOnClickListener{
            Toast.makeText(it.context,"seleccione una ciudad", Toast.LENGTH_LONG).show()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val valor = parent?.getItemAtPosition(position)
        city.isFocusable = true
        city.isClickable = true
        city.setText(valor.toString())
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        city.isFocusable = false
        city.isClickable = false
    }

    private fun updateUser(
        nombre:String, apellidos:String,celular: String,
        nacimiento:String, ciudad:String,urlFoto:String,registro:String) {

        db.collection("users").document(user?.uid.toString()).set(
            hashMapOf(
                "Nombres" to nombre,
                "Apellidos" to apellidos,
                "Celular" to celular,
                "fechaNacimiento" to nacimiento,
                "Ciudad" to ciudad,
                "UrlFoto" to urlFoto,
                "Registro" to registro
            )
        )
    }

    override fun onClick(v: View?) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val recogerFecha = DatePickerDialog(requireContext(), R.style.DatePickerTheme,{ view, year, month, day ->
            val mes = month + 1
            val dia = if (day < 10) "0$day" else day.toString()
            val mesFormateado = if (mes < 10) "0$mes" else mes.toString()
            fechaNacimiento.setText("$dia/$mesFormateado/$year")
        }, year, month, day)
        recogerFecha.datePicker.maxDate = c.timeInMillis
        recogerFecha.show()
    }

    private fun cargarFoto(context: Context,foto: ImageView,url:String) {
        if (url != ""){
            val downloadUri:Uri =url.toUri()
            Glide.with(context /* context */)
                .load(downloadUri)
                .fitCenter()
                .centerCrop()
                .into(foto)
        }
    }

}























//---------------------------------------------------------------------------------------------------
//ESTO SIRVE PARA TRAER MULTIPLES DATOS DE FIREBASE REALTIME
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

//-----------------------------------------------------------------------------------------------------
//   SIRVE PARA LLENAR UN ARRAYLIST CON DATOS TRAIDOS DE FIREBASE
//        // capturamos los datos de firebase firestore de la coleccion ciudades y los ponemos en el listData
//        db.collection("ciudades")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    val nombre = document.data["ciudad"].toString()
//                    listData.add(nombre)
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d("Documento: ", "Error getting documents: ", exception)
//            }

//        //llenar el spinner con los valores de firebase de ciudades y logica de eleccion de item
//        adaptador= ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,listData)
//        spCiudad.adapter = adaptador

//-----------------------------------------------------------------------------------------------
// CODIGO BASADO EN OBTENER Y ACTUALIZAR LOS DATOS CON REALTIME

/*
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
}*/
