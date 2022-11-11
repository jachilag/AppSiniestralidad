package com.example.appsiniestralidadkotlin.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appsiniestralidadkotlin.R
import com.example.appsiniestralidadkotlin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var signup: TextView
    lateinit var home: Button
    lateinit var recuperar: TextView
    lateinit var iniGoogle: Button
    lateinit var iniFacebook:Button
    lateinit var firebaseAuth: FirebaseAuth
    //lateinit var authStateListener:AuthStateListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        binding= ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        var email = findViewById<EditText>(R.id.login_email)
        var password = findViewById<EditText>(R.id.login_password)

        signup = findViewById(R.id.textView_registrarse)
        signup.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        home = findViewById(R.id.btn_log_ini)
        home.setOnClickListener {
           login(email.text.toString(),password.text.toString())
        }

        recuperar = findViewById(R.id.textView_olvidar)
        recuperar.setOnClickListener {
            startActivity(Intent(this,RestoreActivity::class.java))
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

    fun login(email:String, password:String){
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                    Task->if(Task.isSuccessful){
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext,user?.uid.toString(),Toast.LENGTH_LONG).show()
                startActivity(Intent(this,HomeActivity::class.java))
            }else{
                Toast.makeText(baseContext,"Error",Toast.LENGTH_LONG).show()
            }
            }
    }
}