package com.day_a_log.src.add.routine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.day_a_log.databinding.ItemAddRoutineBinding
import com.day_a_log.src.add.routine.models.AddRoutineItem

class AddRoutineAdaptor(private val addRoutineItemList : ArrayList<AddRoutineItem>) : RecyclerView.Adapter<AddRoutineAdaptor.AddRoutineViewHolder>() {

    inner class AddRoutineViewHolder(private val binding: ItemAddRoutineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:AddRoutineItem) {
            binding.tvNum.text = data.num
            binding.tvLoc.text = data.loc
            binding.tvAct.text = data.act
            binding.tvTime.text = data.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddRoutineViewHolder {
        val binding = ItemAddRoutineBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AddRoutineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddRoutineViewHolder, position: Int) {
        holder.bind(addRoutineItemList[position])
    }

    override fun getItemCount(): Int {
        return addRoutineItemList.size
    }
}