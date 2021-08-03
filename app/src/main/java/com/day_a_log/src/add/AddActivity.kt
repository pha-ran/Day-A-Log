package com.day_a_log.src.add

import android.os.Bundle
import com.day_a_log.config.BaseActivity
import com.day_a_log.databinding.ActivityAddBinding

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showCustomToast("test")
    }
}