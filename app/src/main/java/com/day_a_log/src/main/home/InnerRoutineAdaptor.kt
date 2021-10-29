package com.day_a_log.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.day_a_log.databinding.ItemRoutineFirst2Binding
import com.day_a_log.databinding.ItemRoutineMidBinding

class InnerRoutineAdaptor(private val innerRoutinesItemList : Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //ToDo 임시로 고정 아이템 사용

    inner class InnerRoutineViewHolder(private val binding: ItemRoutineFirst2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Int) {
            binding.imageView5.setImageResource(data)
        }
    }

    inner class InnerRoutineMidViewHolder(private val binding: ItemRoutineMidBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Int) {
            binding.iv.setImageResource(data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val binding = ItemRoutineMidBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                InnerRoutineMidViewHolder(binding)
            }
            else -> {
                val binding = ItemRoutineFirst2Binding.inflate(LayoutInflater.from(parent.context), parent, false)

                InnerRoutineViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> {
                (holder as InnerRoutineMidViewHolder).bind(innerRoutinesItemList)
            }
            else -> {
                (holder as InnerRoutineViewHolder).bind(innerRoutinesItemList)
            }
        }
    }

    override fun getItemCount(): Int {
        return 3//innerRoutinesItemList.size
    }
}