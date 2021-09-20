package com.day_a_log.src.add.log

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentAddLogBinding
import com.day_a_log.src.add.AddActivity
import com.day_a_log.src.add.log.models.AddLogItem

class AddLogFragment : BaseFragment<FragmentAddLogBinding>(FragmentAddLogBinding::bind, R.layout.fragment_add_log) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etTitle.setText((activity as AddActivity).title)

        setItem()
        val adaptor = AddLogAdaptor((activity as AddActivity).addLogItemList)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvAddLog.layoutManager = layoutManager
        binding.rvAddLog.adapter = adaptor
        PagerSnapHelper().attachToRecyclerView(binding.rvAddLog)

        binding.button.setOnClickListener {
            (activity as AddActivity).openGallery()
            binding.ivImg.setImageBitmap((activity as AddActivity).currentBitmap)
        }
    }

    override fun onDestroyView() {
        (activity as AddActivity).title = binding.etTitle.text?.toString()
        super.onDestroyView()
    }

    fun setItem() {
        val s = (activity as AddActivity).addRoutineItemList
        (activity as AddActivity).addLogItemList.clear()
        for(i in 1..s.size) {
            (activity as AddActivity).addLogItemList.add(AddLogItem(
                s[i-1].num,
                s[i-1].loc,
                s[i-1].act,
                s[i-1].time,
                null
            ))
        }
    }

    fun getTitle(): String {
        return binding.etTitle.text.toString()
    }
}