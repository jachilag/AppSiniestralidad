package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
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


class menuFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var idUser:String
    lateinit var cardPerfil:CardView
    lateinit var cardReportar:CardView
    lateinit var cardNoticias:CardView
    lateinit var cardConfiguraciones:CardView
    lateinit var cardEmergencias:CardView
    lateinit var cardAyuda:CardView
    lateinit var otro:CardView
    lateinit var imgPerfil:ImageView
    lateinit var urlFoto:String
    lateinit var storageRef : StorageReference
    lateinit var nombre: String
    lateinit var apellido: String
    val db = FirebaseFirestore.getInstance()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        firebaseAuth = Firebase.auth
        idUser = firebaseAuth.currentUser?.uid.toString()
        storageRef = FirebaseStorage.getInstance().reference
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        imgPerfil = view.findViewById(R.id.img_avatarVerde)
        cardPerfil = view.findViewById(R.id.fragPerfil)
        cardReportar = view.findViewById(R.id.fragReportar)
        cardNoticias = view.findViewById(R.id.fragNoticias)
        cardConfiguraciones = view.findViewById(R.id.fragConfiguraciones)
        cardEmergencias = view.findViewById(R.id.fragEmergencias)
        cardAyuda = view.findViewById(R.id.fragAyuda)
        otro = view.findViewById(R.id.fragAyuda)
        db.collection("users").document(idUser).get().addOnSuccessListener {
            urlFoto = it.get("UrlFoto") as String
            cargarFoto(requireContext(),imgPerfil,urlFoto)
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         
        cardPerfil.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_perfilFragment)
        }

        cardReportar.setOnClickListener {
            db.collection("users").document(idUser).get().addOnSuccessListener {
                nombre = it.get("Nombres") as String
                apellido = it.get("Apellidos") as String
                if(nombre == "" || apellido == ""){ventana()}
                else {findNavController().navigate(R.id.action_menuFragment_to_reportarFragment)}
            }
        }

        cardNoticias.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_noticiasFragment)
        }

        cardConfiguraciones.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_configuracionesFragment)
        }

        cardEmergencias.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_emergenciasFragment)
        }

        cardAyuda.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_ayudaFragment)
        }

        otro.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_ayudaFragment)
        }

    }

    private fun cargarFoto(context: Context, foto: ImageView, url:String) {
        if (url != ""){
            val downloadUri: Uri =url.toUri()
            Glide.with(context /* context */)
                .load(downloadUri)
                .fitCenter()
                .centerCrop()
                .into(foto)
        }
    }

    private fun ventana() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setTitle("COMPLETE SU PERFIL")
        builder.setMessage("Para realizar reportes, porfavor complete su perfil¡¡")
        builder.setPositiveButton("Ok"){
                dialog,which->
            findNavController().navigate(R.id.action_menuFragment_to_perfilFragment)
        }
        builder.show()
    }
}