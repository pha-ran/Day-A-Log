package com.day_a_log.src.add.routine

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentAddRoutineBinding
import com.day_a_log.src.add.AddActivity
import com.day_a_log.src.add.routine.models.AddRoutineItem

class AddRoutineFragment : BaseFragment<FragmentAddRoutineBinding>(FragmentAddRoutineBinding::bind, R.layout.fragment_add_routine) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adaptor = AddRoutineAdaptor((activity as AddActivity).addRoutineItemList)
        binding.rvAdd.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAdd.adapter = adaptor

        binding.etTitle.setText((activity as AddActivity).title)
        changeIndex()
        changeSize()

        binding.ivAdd.setOnClickListener {
            if ((activity as AddActivity).addRoutineItemList.size < 5) {
                (activity as AddActivity).addRoutineItemList.add(
                    AddRoutineItem(
                        ((activity as AddActivity).addRoutineItemList.size + 1).toString(),
                        binding.etLoc.text.toString(),
                        binding.etAct.text.toString(),
                        binding.etTime.text.toString()
                    )
                )

                adaptor.notifyDataSetChanged()
                binding.etLoc.text = null
                binding.etAct.text = null
                binding.etTime.text = null
                changeIndex()
                changeSize()
            }
        }

        adaptor.setItemClickListener(object : AddRoutineAdaptor.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                val item = (activity as AddActivity).addRoutineItemList[position]
                showCustomToast("x버튼 클릭, ${item.loc}")
                (activity as AddActivity).addRoutineItemList.removeAt(position)
                changeIndex()
                changeSize()
                adaptor.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroyView() {
        (activity as AddActivity).title = binding.etTitle.text?.toString()
        super.onDestroyView()
    }

    fun changeIndex() {
        val s = (activity as AddActivity).addRoutineItemList.size
        for(i in 1..s) {
            (activity as AddActivity).addRoutineItemList[i-1].num = i.toString()
        }
        binding.tvNum.text = ((activity as AddActivity).addRoutineItemList.size + 1).toString()
    }

    fun changeSize() {
        if ((activity as AddActivity).addRoutineItemList.size >= 5) {
            binding.linearAdd.visibility = View.GONE
        }
        else {
            binding.linearAdd.visibility = View.VISIBLE
        }
    }
}