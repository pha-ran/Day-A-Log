package com.day_a_log.src.main

import android.os.Bundle
import com.day_a_log.R
import com.day_a_log.config.BaseActivity
import com.day_a_log.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Day_A_Log)
        super.onCreate(savedInstanceState)


    }
}