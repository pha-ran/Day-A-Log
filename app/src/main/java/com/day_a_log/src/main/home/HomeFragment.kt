package com.day_a_log.src.main.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentHomeBinding
import com.day_a_log.src.add.AddActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.tb_home_hash -> {

                }
                R.id.tb_home_search -> {

                }
                R.id.tb_home_add -> {
                    activity!!.startActivity(Intent(activity, AddActivity::class.java))
                }
            }
            true
        }
    }
}