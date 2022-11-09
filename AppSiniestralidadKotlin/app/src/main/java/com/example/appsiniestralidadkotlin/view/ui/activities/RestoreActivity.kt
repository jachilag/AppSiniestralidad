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

class RestoreActivity : AppCompatActivity() {
    lateinit var authStateListener: FirebaseAuth.AuthStateListener
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore)

        firebaseAuth = Firebase.auth
        var email = findViewById<EditText>(R.id.text_correo_recuperacion)

        var register: Button = findViewById(R.id.btn_recuperar)
        register.setOnClickListener {
            Toast.makeText(this, "RECUPERANDO CONTRASEÑA", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,LoginActivity::class.java))}
    }
}