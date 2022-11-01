package com.example.appsiniestralidadkotlin.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.appsiniestralidadkotlin.R

class SignUpActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var register: Button = findViewById(R.id.btn_registrarse)
        register.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}