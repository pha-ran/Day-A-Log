package com.day_a_log.src.add.log

import android.os.Bundle
import android.view.View
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentAddLogBinding
import com.day_a_log.src.add.AddActivity

class AddLogFragment : BaseFragment<FragmentAddLogBinding>(FragmentAddLogBinding::bind, R.layout.fragment_add_log) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etTitle.setText((activity as AddActivity).title)
    }

    override fun onDestroyView() {
        (activity as AddActivity).title = binding.etTitle.text?.toString()
        super.onDestroyView()
    }
}