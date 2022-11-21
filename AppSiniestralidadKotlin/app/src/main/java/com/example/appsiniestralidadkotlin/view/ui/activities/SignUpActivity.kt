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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity: AppCompatActivity() {
    lateinit var authStateListener: FirebaseAuth.AuthStateListener
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth = Firebase.auth
        var email = findViewById<EditText>(R.id.register_birthday)
        var password = findViewById<EditText>(R.id.registrar_password)

        // creacion de usurio en la coleccion user que no es la misma de la autenticacion
//        val formater = SimpleDateFormat("dd-MM-yyyy")
//        var fechaNacimiento =  formater.parse("01-01-1900")
        databaseReference = Firebase.database.reference.child("users")
        var name = ""
        var apellido = ""
        var celular = 0L
        var fechaNacimiento = "01/01/1900"
        var ciudad = ""


        var register: Button = findViewById(R.id.btn_registrarse)
        register.setOnClickListener {
            createUser(email.text.toString(),password.text.toString(), name,
                apellido,celular,fechaNacimiento,ciudad)
        }
    }

    fun createUser(email:String, password:String, name: String, apellido: String, celular:Long,
    nacimiento:String,ciudad:String){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                    Task->if(Task.isSuccessful){
                val user = firebaseAuth.currentUser
                val userdb = databaseReference.child(user?.uid.toString())
                userdb.child("Nombres").setValue(name)
                userdb.child("Apellidos").setValue(apellido)
                userdb.child("Celular").setValue(celular)
                userdb.child("fechaNacimiento").setValue(nacimiento)
                userdb.child("Ciudad").setValue(ciudad)
                userdb.child("Correo").setValue(email)

                Toast.makeText(applicationContext,"USUARIO REGISTRADO", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,LoginActivity::class.java))
            }else{
                Toast.makeText(applicationContext,"Error", Toast.LENGTH_LONG).show()
            }
            }

    }
}