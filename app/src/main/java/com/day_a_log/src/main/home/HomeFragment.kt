package com.day_a_log.src.main.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentHomeBinding
import com.day_a_log.src.add.AddActivity
import com.day_a_log.src.main.home.models.RoutinesResponse

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
                        RoutinesView{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(requireContext())
        RoutinesService(this).tryGetRoutines()

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

    override fun onGetRoutinesSuccess(response: RoutinesResponse) {
        dismissLoadingDialog()
        showCustomToast(response.result[0].idx.toString())
    }

    override fun onGetRoutinesFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}