package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
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
import java.io.ByteArrayOutputStream

class FotoPerfilFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var idUser:String
    lateinit var btn_camara: Button
    lateinit var btn_galeria: Button
    lateinit var btn_actualizar: Button
    lateinit var foto: ImageView
    lateinit var storageRef : StorageReference
    private val CAMARA = 123
    private val GALERIA = 456
    lateinit var downloadUri: Uri
    val db = FirebaseFirestore.getInstance()
    lateinit var name:String
    lateinit var fechaNacimiento:String
    lateinit var apellido:String
    lateinit var celular:String
    lateinit var city:String
    lateinit var urlFoto:String
    lateinit var registro:String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        firebaseAuth = Firebase.auth
        idUser = firebaseAuth.currentUser?.uid.toString()

        //FIRESTORAGE
        storageRef = FirebaseStorage.getInstance().reference

        val view = inflater.inflate(R.layout.fragment_fotoperfil, container, false)
        btn_camara = view.findViewById(R.id.btn_camara)
        btn_galeria = view.findViewById(R.id.btn_galeria)
        btn_actualizar = view.findViewById(R.id.btn_actualizarFoto)
        foto = view.findViewById(R.id.img_perfil_2)

        cargarDatosUsuario()
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //listener para activar la camara y tomar una foto
        btn_camara.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMARA)
        }

        //listener para activar la apertura de galeria
        btn_galeria.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALERIA)
        }

        //listener boton actualizar para regresar a los datos de perfil
        btn_actualizar.setOnClickListener {
            findNavController().navigate(R.id.action_fotoPerfilFragment_to_perfilFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uri: Uri? = data?.data
        val fotoPerfilRef = storageRef.child("fotosPerfil/$idUser")

        //SI LA FOTO ES DE LA CAMARA
        if(requestCode == CAMARA && resultCode == RESULT_OK){
            var bitmap = data?.extras?.get("data") as Bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            var uploadTask = fotoPerfilRef.putBytes(data)
            uploadTask
                .addOnFailureListener { Toast.makeText(requireContext(),"FALLÓ LA CARGA!!", Toast.LENGTH_LONG).show()}
                .addOnSuccessListener { taskSnapshot ->

                    val urlTask = uploadTask.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                            }
                        }
                        fotoPerfilRef.downloadUrl
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            downloadUri = task.result
                            urlFoto = downloadUri.toString()
                            cargarFoto(requireContext(),foto,urlFoto)
                            updateUser(name,apellido,celular,fechaNacimiento,city,urlFoto,registro)
                            Toast.makeText(requireContext(),"FOTO DE PERFIL CARGADA", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(requireContext(),"FALLÓ LA CARGA!!", Toast.LENGTH_LONG).show()}
                    }
                }
        }


        // SI LA FOTO ES DE LA GALERIA
        else if (requestCode == GALERIA && resultCode == RESULT_OK){
            var uploadTask = fotoPerfilRef.putFile(uri!!)
            uploadTask
            .addOnFailureListener {Toast.makeText(requireContext(),"FALLÓ LA CARGA!!",Toast.LENGTH_LONG).show()}
            .addOnSuccessListener { taskSnapshot ->

                val urlTask = uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    fotoPerfilRef.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        downloadUri = task.result
                        urlFoto = downloadUri.toString()
                        cargarFoto(requireContext(),foto,urlFoto)
                        updateUser(name,apellido,celular,fechaNacimiento,city,urlFoto,registro)
                        Toast.makeText(requireContext(),"FOTO DE PERFIL CARGADA",Toast.LENGTH_LONG).show()
                    } else {Toast.makeText(requireContext(),"FALLÓ LA CARGA!!",Toast.LENGTH_LONG).show()}
                }
            }
        }


    }

    private fun updateUser(
    nombre:String, apellidos:String,celular: String,
    nacimiento:String, ciudad:String,urlFoto:String,registro:String) {
        db.collection("users").document(idUser).set(
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


    fun cargarDatosUsuario(){
        db.collection("users").document(idUser).get().addOnSuccessListener {
            name = (it.get("Nombres") as String?).toString()
            fechaNacimiento = (it.get("fechaNacimiento") as String?).toString()
            apellido = (it.get("Apellidos") as String?).toString()
            celular = (it.get("Celular") as String?).toString()
            city = (it.get("Ciudad")as String?).toString()
            urlFoto = it.get("UrlFoto") as String
            registro = (it.get("Registro")as String?).toString()
            cargarFoto(requireContext(),foto,urlFoto)
        }
    }


    private fun ventana(mensaje:String){
        val builder= AlertDialog.Builder(requireContext())
        builder.setTitle("Gracias por reportar")
        builder.setMessage(mensaje)
        builder.setPositiveButton("Ok"){
                dialog,which->
        }
        builder.show()
    }


}

    //https://drive.google.com/file/d/10Jrcl7znqO-IbstjQyopYoDvHzjW9-4C/view?usp=share_link