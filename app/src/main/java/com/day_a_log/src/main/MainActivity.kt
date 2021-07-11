package com.day_a_log.src.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.day_a_log.R
import com.day_a_log.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Day_A_Log)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}