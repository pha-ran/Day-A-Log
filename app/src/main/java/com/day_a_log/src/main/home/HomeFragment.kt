package com.day_a_log.src.main.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentHomeBinding
import com.day_a_log.src.add.AddActivity
import com.day_a_log.src.main.home.models.RoutinesResult
import com.day_a_log.src.main.home.models.RoutinesResponse

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
                        RoutinesView{
    lateinit var glideManager : RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        glideManager = Glide.with(this)
        getRoutines()

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

    private fun getRoutines() {
        showLoadingDialog(requireContext())
        RoutinesService(this).tryGetRoutines()
    }

    override fun onGetRoutinesSuccess(response: RoutinesResponse) {
        dismissLoadingDialog()
        showCustomToast(response.result[0].idx.toString())

        setRecyclerView(response.result)
    }

    override fun onGetRoutinesFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    private fun setRecyclerView(data : List<RoutinesResult>) {
        val adaptor = HomeAdaptor(data, glideManager, requireContext())
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rv.layoutManager = layoutManager
        binding.rv.adapter = adaptor
    }
}