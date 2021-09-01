package com.day_a_log.src.add.routine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.day_a_log.databinding.ItemAddRoutineBinding
import com.day_a_log.src.add.routine.models.AddRoutineItem

class AddRoutineAdaptor(private val addRoutineItemList : ArrayList<AddRoutineItem>) : RecyclerView.Adapter<AddRoutineAdaptor.AddRoutineViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    lateinit var onItemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener : OnItemClickListener) {
        this.onItemClickListener = itemClickListener
    }

    inner class AddRoutineViewHolder(private val binding: ItemAddRoutineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:AddRoutineItem) {
            binding.tvNum.text = data.num
            binding.tvLoc.text = data.loc
            binding.tvAct.text = data.act
            binding.tvTime.text = data.time
        }
        fun bindingIvDelete(): ImageView {
            return binding.ivDelete
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddRoutineViewHolder {
        val binding = ItemAddRoutineBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AddRoutineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddRoutineViewHolder, position: Int) {
        holder.bind(addRoutineItemList[position])

        holder.bindingIvDelete().setOnClickListener {
            onItemClickListener.onItemClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return addRoutineItemList.size
    }
}