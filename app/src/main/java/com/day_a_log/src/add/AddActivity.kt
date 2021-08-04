package com.day_a_log.src.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.day_a_log.R
import com.day_a_log.config.BaseActivity
import com.day_a_log.databinding.ActivityAddBinding

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showCustomToast("test")

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_activity_add_back)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tb_add_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.tb_add_next -> {
                showCustomToast("next")
            }
        }

        return super.onOptionsItemSelected(item)
    }
}