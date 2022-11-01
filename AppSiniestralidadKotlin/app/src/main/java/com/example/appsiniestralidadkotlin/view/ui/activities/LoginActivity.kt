package com.example.appsiniestralidadkotlin.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appsiniestralidadkotlin.R
import com.example.appsiniestralidadkotlin.databinding.ActivityMainBinding

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var signup: TextView
    lateinit var home: Button
    lateinit var recuperar: TextView
    lateinit var iniGoogle: Button
    lateinit var iniFacebook:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        binding= ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        signup = findViewById(R.id.textView_registrarse)
        signup.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        home = findViewById(R.id.btn_log_ini)
        home.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }

        recuperar = findViewById(R.id.textView_olvidar)
        recuperar.setOnClickListener {
            Toast.makeText(this, "Recuperando Contraseña..", Toast.LENGTH_LONG).show()
        }

        iniGoogle = findViewById(R.id.btn_log_google)
        iniGoogle.setOnClickListener {
            Toast.makeText(this,"Redirigiendo a google", Toast.LENGTH_LONG).show()
        }

        iniFacebook = findViewById(R.id.btn_log_facebook)
        iniFacebook.setOnClickListener {
            Toast.makeText(this,"Redirigiendo a Facebook..", Toast.LENGTH_LONG).show()
        }
    }
}