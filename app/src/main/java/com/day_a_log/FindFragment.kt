package com.day_a_log

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.day_a_log.databinding.FragmentFindBinding

class FindFragment : Fragment() {
    private lateinit var binding : FragmentFindBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFindBinding.inflate(inflater, container, false)

        return binding.root
    }
}