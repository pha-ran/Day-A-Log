package com.day_a_log.src.add.log

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
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

        setSlidingUpPanel()

        binding.btnImg1.setOnClickListener {
            (activity as AddActivity).openGallery(1)
        }

        binding.btnImg2.setOnClickListener {
            (activity as AddActivity).openGallery(2)
        }

        binding.btnImg3.setOnClickListener {
            (activity as AddActivity).openGallery(3)
        }

        binding.btnImg4.setOnClickListener {
            (activity as AddActivity).openGallery(4)
        }

        binding.btnImg5.setOnClickListener {
            (activity as AddActivity).openGallery(5)
        }
    }

    override fun onDestroyView() {
        (activity as AddActivity).title = binding.etTitle.text?.toString()
        super.onDestroyView()
    }

    private fun setItem() {
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

    private fun setSlidingUpPanel() {
        val i = (activity as AddActivity).addRoutineItemList.size

        binding.linearImg1.visibility = View.INVISIBLE
        binding.linearImg2.visibility = View.INVISIBLE
        binding.linearImg3.visibility = View.INVISIBLE
        binding.linearImg4.visibility = View.INVISIBLE
        binding.linearImg5.visibility = View.INVISIBLE

        if (i >= 1)
            binding.linearImg1.visibility = View.VISIBLE
        if (i >= 2)
            binding.linearImg2.visibility = View.VISIBLE
        if (i >= 3)
            binding.linearImg3.visibility = View.VISIBLE
        if (i >= 4)
            binding.linearImg4.visibility = View.VISIBLE
        if (i >= 5)
            binding.linearImg5.visibility = View.VISIBLE
    }

    fun setImage(i : Int, it : Uri) {
        when (i) {
            1 -> {
                binding.ivImg1.visibility = View.VISIBLE
                Glide.with(this)
                    .load(it)
                    .into(binding.ivImg1)
            }
            2 -> {
                //ToDo
            }
            3 -> {

            }
            4 -> {

            }
            5 -> {

            }
        }
    }

    fun getTitle(): String {
        return binding.etTitle.text.toString()
    }
}