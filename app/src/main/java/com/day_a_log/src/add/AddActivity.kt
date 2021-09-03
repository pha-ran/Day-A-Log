package com.day_a_log.src.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.day_a_log.R
import com.day_a_log.config.BaseActivity
import com.day_a_log.databinding.ActivityAddBinding
import com.day_a_log.src.add.log.AddLogFragment
import com.day_a_log.src.add.routine.AddRoutineFragment
import com.day_a_log.src.add.routine.models.AddRoutineItem

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate) {

    internal val addRoutineItemList = ArrayList<AddRoutineItem>()
    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_activity_add_back)

        replaceFragment(0)
    }

    private fun replaceFragment(p : Int) {
        if (p == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AddRoutineFragment()).commitAllowingStateLoss()
            page = 0
        }
        else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AddLogFragment()).commitAllowingStateLoss()
            page = 1
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tb_add_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (page == 0) {
                    finish()
                }
                else {
                    replaceFragment(0)
                }
            }
            R.id.tb_add_next -> {
                if (page == 0) {
                    replaceFragment(1)
                }
                else {
                    showCustomToast("업로드")
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}