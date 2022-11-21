package com.example.appsiniestralidadkotlin.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.appsiniestralidadkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var handler: Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // asociar activity con el layout
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // configuracion para que funcione la animacion
        binding.animationView.playAnimation()

        //transicion animacion  y la siguiente activity
        handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}