package com.example.appsiniestralidadkotlin.view.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appsiniestralidadkotlin.R
import com.example.appsiniestralidadkotlin.databinding.ActivityMainBinding

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        binding= ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
    }
}