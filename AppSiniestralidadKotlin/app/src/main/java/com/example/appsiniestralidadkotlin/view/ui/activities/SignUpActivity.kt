package com.example.appsiniestralidadkotlin.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appsiniestralidadkotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SignUpActivity: AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth = Firebase.auth
        var email = findViewById<EditText>(R.id.register_birthday)
        var password = findViewById<EditText>(R.id.registrar_password)

        // creacion de usurio en la coleccion user que no es la misma de la autenticacion
        var name = ""
        var apellido = ""
        var celular = ""
        var fechaNacimiento = ""
        var ciudad = ""
        var urlFoto = ""
        var registro = ""
        var register: Button = findViewById(R.id.btn_registrarse)

        register.setOnClickListener {
            createUser(email.text.toString(),password.text.toString(), name,
                apellido,celular,fechaNacimiento,ciudad,urlFoto,registro)
        }
    }
//-------------------------------------------------------------------------------------------------
//    utilizacion de  CLOUD STORE para guardar datos vacios de usuarios
    fun createUser(email:String, password:String, name: String, apellido: String, celular:String,
    nacimiento:String,ciudad:String,urlFoto:String,registro:String) {
    firebaseAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this) { Task ->
            if (Task.isSuccessful) {
                val user = firebaseAuth.currentUser

                db.collection("users").document(user?.uid.toString()).set(
                    hashMapOf(
                        "Nombres" to name,
                        "Apellidos" to apellido,
                        "Celular" to celular,
                        "fechaNacimiento" to nacimiento,
                        "Ciudad" to ciudad,
                        "Correo" to email,
                        "UrlFoto" to urlFoto,
                        "Registro" to registro
                    )
                )
                Toast.makeText(applicationContext, "USUARIO REGISTRADO", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }
        }
    }
}




//--------------------------------------------------
// uso de REALTIME de FIRESTORE

//class SignUpActivity: AppCompatActivity() {
//    lateinit var authStateListener: FirebaseAuth.AuthStateListener
//    lateinit var firebaseAuth: FirebaseAuth
//    lateinit var databaseReference: DatabaseReference
//    lateinit var database: FirebaseDatabase
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_signup)
//
//        firebaseAuth = Firebase.auth
//        var email = findViewById<EditText>(R.id.register_birthday)
//        var password = findViewById<EditText>(R.id.registrar_password)
//
//        // creacion de usurio en la coleccion user que no es la misma de la autenticacion
//        databaseReference = Firebase.database.reference.child("users")
//        var name = ""
//        var apellido = ""
//        var celular = ""
//        var fechaNacimiento = "01/01/1900"
//        var ciudad = "Bogotá"
//        var urlFoto = ""
//
//
//        var register: Button = findViewById(R.id.btn_registrarse)
//        register.setOnClickListener {
//            createUser(
//                email.text.toString(), password.text.toString(), name,
//                apellido, celular, fechaNacimiento, ciudad, urlFoto
//            )
//        }
//    }
//
//    //-------------------------------------------------------------------------------------------------
//    //utilizacion de REALTIME para guardar datos vacios de usuarios
//    fun createUser(
//        email: String, password: String, name: String, apellido: String, celular: String,
//        nacimiento: String, ciudad: String, urlFoto: String
//    ) {
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { Task ->
//                if (Task.isSuccessful) {
//                    val user = firebaseAuth.currentUser
//                    val userdb = databaseReference.child(user?.uid.toString())
//                    userdb.child("Nombres").setValue(name)
//                    userdb.child("Apellidos").setValue(apellido)
//                    userdb.child("Celular").setValue(celular)
//                    userdb.child("fechaNacimiento").setValue(nacimiento)
//                    userdb.child("Ciudad").setValue(ciudad)
//                    userdb.child("Correo").setValue(email)
//                    userdb.child("UrlFoto").setValue(urlFoto)
//
//                    Toast.makeText(applicationContext, "USUARIO REGISTRADO", Toast.LENGTH_LONG)
//                        .show()
//                    startActivity(Intent(this, LoginActivity::class.java))
//                } else {
//                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
//                }
//            }
//
//    }
//}

