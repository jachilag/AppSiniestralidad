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
import com.google.firebase.ktx.Firebase

class SignUpActivity: AppCompatActivity() {
    lateinit var authStateListener: FirebaseAuth.AuthStateListener
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth = Firebase.auth
        var email = findViewById<EditText>(R.id.registrar_email)
        var password = findViewById<EditText>(R.id.registrar_password)

        var register: Button = findViewById(R.id.btn_registrarse)
        register.setOnClickListener {
            createUser(email.text.toString(),password.text.toString())
        }
    }
    fun createUser(email:String, password:String){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                    Task->if(Task.isSuccessful){
                val user = firebaseAuth.currentUser
                startActivity(Intent(this,LoginActivity::class.java))
            }else{
                Toast.makeText(applicationContext,"Error", Toast.LENGTH_LONG).show()
            }
            }

    }
}