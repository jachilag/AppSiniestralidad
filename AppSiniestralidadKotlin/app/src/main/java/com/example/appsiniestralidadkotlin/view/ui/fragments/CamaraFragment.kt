package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils.substring
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appsiniestralidadkotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class CamaraFragment: Fragment() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var idUser:String
    lateinit var imgSiniestro:ImageView
    lateinit var btnCamara: Button
    lateinit var btnGaleria: Button
    lateinit var btn_continuar: Button
    lateinit var storageRef : StorageReference
    lateinit var urlFoto:String
    private val CAMARA = 123
    private val GALERIA = 456
    lateinit var downloadUri: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        firebaseAuth = Firebase.auth
        idUser = firebaseAuth.currentUser?.uid.toString()

        //FIRESTORAGE
        storageRef = FirebaseStorage.getInstance().reference

        val view = inflater.inflate(R.layout.fragment_camara, container, false)
        btn_continuar = view.findViewById(R.id.btn_avanzar_to_descripcion)
        imgSiniestro = view.findViewById(R.id.img_siniestro)
        btnCamara = view.findViewById(R.id.btn_camara_siniestro)
        btnGaleria = view.findViewById(R.id.btn_galeria_siniestro)

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val involucrados = arguments?.getString("involucrados")
        val latitud = arguments?.getString("latitud")
        val longitud = arguments?.getString("longitud")



        btn_continuar.setOnClickListener {
            val bundle = bundleOf(
                "involucrados" to involucrados,
                "latitud" to latitud,
                "longitud" to longitud,
                "url" to urlFoto,
            )
            findNavController().navigate(R.id.action_camaraFragment_to_descripcionFragment,bundle)
        }

        //listener para activar la camara y tomar una foto
        btnCamara.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMARA)
        }

        //listener para activar la apertura de galeria
        btnGaleria.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALERIA)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val date = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val shortIdUser = substring(idUser,0,5)
        val fotoSiniestroRef = storageRef.child("fotosSiniestros/$date-$shortIdUser")

        //SI LA FOTO ES DE LA CAMARA
        if(requestCode == CAMARA && resultCode == Activity.RESULT_OK){
            var bitmap = data?.extras?.get("data") as Bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            var uploadTask = fotoSiniestroRef.putBytes(data)
            uploadTask
                .addOnFailureListener { Toast.makeText(requireContext(),"FALLÓ LA CARGA!!", Toast.LENGTH_LONG).show()}
                .addOnSuccessListener { taskSnapshot ->

                    val urlTask = uploadTask.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                            }
                        }
                        fotoSiniestroRef.downloadUrl
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            downloadUri = task.result
                            urlFoto = downloadUri.toString()
                            cargarFoto(requireContext(),imgSiniestro,urlFoto)
                            Toast.makeText(requireContext(),"FOTO DE SINIESTRO CARGADA", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(requireContext(),"FALLÓ LA CARGA!!", Toast.LENGTH_LONG).show()}
                    }
                }
        }


        // SI LA FOTO ES DE LA GALERIA
        else if (requestCode == GALERIA && resultCode == Activity.RESULT_OK){
            val uri: Uri? = data?.data
            var uploadTask = fotoSiniestroRef.putFile(uri!!)
            uploadTask
                .addOnFailureListener { Toast.makeText(requireContext(),"FALLÓ LA CARGA!!", Toast.LENGTH_LONG).show()}
                .addOnSuccessListener { taskSnapshot ->

                    val urlTask = uploadTask.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                            }
                        }
                        fotoSiniestroRef.downloadUrl
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            downloadUri = task.result
                            urlFoto = downloadUri.toString()
                            cargarFoto(requireContext(),imgSiniestro,urlFoto)
                            Toast.makeText(requireContext(),"FOTO DE SINIESTRO CARGADA", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(requireContext(),"FALLÓ LA CARGA!!", Toast.LENGTH_LONG).show()}
                    }
                }
        }
    }

    private fun cargarFoto(context: Context, foto: ImageView, url:String) {
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





//https://drive.google.com/file/d/10Jrcl7znqO-IbstjQyopYoDvHzjW9-4C/view?usp=share_link