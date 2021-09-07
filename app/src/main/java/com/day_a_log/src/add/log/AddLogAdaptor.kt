package com.day_a_log.src.add.log

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.day_a_log.databinding.ItemAddLogBinding
import com.day_a_log.src.add.log.models.AddLogItem

class AddLogAdaptor(private val addLogItemList : ArrayList<AddLogItem>) : RecyclerView.Adapter<AddLogAdaptor.AddLogViewHolder>() {
    inner class AddLogViewHolder(private val binding: ItemAddLogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AddLogItem) {
            binding.tvNum.text = data.num
            binding.tvLoc.text = data.loc
            binding.tvAct.text = data.act
            binding.tvTime.text = data.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddLogViewHolder {
        val binding = ItemAddLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AddLogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddLogViewHolder, position: Int) {
        holder.bind(addLogItemList[position])
    }

    override fun getItemCount(): Int {
        return addLogItemList.size
    }
}