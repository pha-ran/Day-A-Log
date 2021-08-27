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

        binding.ivAdd.setOnClickListener {
            if ((activity as AddActivity).addRoutineItemList.size < 5) {
                (activity as AddActivity).addRoutineItemList.add(
                    AddRoutineItem(
                        //아이템이 추가되기 전이라 +1
                        ((activity as AddActivity).addRoutineItemList.size + 1).toString(),
                        binding.etLoc.text.toString(),
                        binding.etAct.text.toString(),
                        binding.etTime.text.toString()
                    )
                )

                adaptor.notifyDataSetChanged()
                binding.tvNum.text = ((activity as AddActivity).addRoutineItemList.size + 1).toString()
                binding.etLoc.text = null
                binding.etAct.text = null
                binding.etTime.text = null
            }

            if ((activity as AddActivity).addRoutineItemList.size >= 5) {
                binding.linearAdd.visibility = View.GONE
            }

            //삭제시 ArrayList.indexOf()로 인덱스값 얻기
        }
    }
}