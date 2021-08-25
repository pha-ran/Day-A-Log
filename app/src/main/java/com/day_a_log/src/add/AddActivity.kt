package com.day_a_log.src.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.day_a_log.R
import com.day_a_log.config.BaseActivity
import com.day_a_log.databinding.ActivityAddBinding
import com.day_a_log.src.add.routine.AddRoutineAdaptor
import com.day_a_log.src.add.routine.models.AddRoutineItem

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate) {

    private val addRoutineItemList = ArrayList<AddRoutineItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_activity_add_back)

        val adaptor = AddRoutineAdaptor(addRoutineItemList)
        binding.rvAdd.layoutManager = LinearLayoutManager(this)
        binding.rvAdd.adapter = adaptor

        binding.ivAdd.setOnClickListener {
            addRoutineItemList.add(AddRoutineItem(
                (addRoutineItemList.size+1).toString(),
                binding.etLoc.text.toString(),
                binding.etAct.text.toString(),
                binding.etTime.text.toString()
            ))
            adaptor.notifyDataSetChanged()
            binding.tvNum.text = (addRoutineItemList.size+1).toString()
            binding.etLoc.text = null
            binding.etAct.text = null
            binding.etTime.text = null
        }
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