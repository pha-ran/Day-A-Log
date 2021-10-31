package com.day_a_log.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.day_a_log.R
import com.day_a_log.databinding.*
import com.day_a_log.src.main.home.models.RoutinesResult

class InnerRoutineAdaptor(private val innerRoutinesItemList : RoutinesResult)
        : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //ToDo API 연동, 주석 보고 기능 추가

    inner class InnerRoutineFirst2ViewHolder(private val binding: ItemRoutineFirst2Binding)
            : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : RoutinesResult) {
            binding.imageView5.setImageResource(R.drawable.ic_bnv_menu_profile_x)
        }
    }

    inner class InnerRoutineFirst3ViewHolder(private val binding: ItemRoutineFirst3Binding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : RoutinesResult) {
            binding.iv1.setImageResource(R.drawable.ic_bnv_menu_profile_x)
        }
    }

    inner class InnerRoutineFirst4ViewHolder(private val binding: ItemRoutineFirst4Binding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : RoutinesResult) {
            binding.iv1.setImageResource(R.drawable.ic_bnv_menu_profile_x)
        }
    }

    inner class InnerRoutineFirst5ViewHolder(private val binding: ItemRoutineFirst5Binding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : RoutinesResult) {
            binding.iv1.setImageResource(R.drawable.ic_bnv_menu_profile_x)
        }
    }

    inner class InnerRoutineMidViewHolder(private val binding: ItemRoutineMidBinding)
            : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : RoutinesResult) {
            binding.iv.setImageResource(R.drawable.ic_bnv_menu_profile_x)
            // binding.tv.text = data.logArray[position] // ToDo 이미지 타이틀 설정
        }
    }

    inner class InnerRoutineLastViewHolder(private val binding: ItemRoutineLastBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : RoutinesResult) {
            binding.iv1.setImageResource(R.drawable.ic_bnv_menu_profile_x)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                return when (innerRoutinesItemList.logArray.size) {
                    3 -> {
                        val binding = ItemRoutineFirst3Binding.inflate(LayoutInflater.from(parent.context), parent, false)

                        InnerRoutineFirst3ViewHolder(binding)
                    }
                    4 -> {
                        val binding = ItemRoutineFirst4Binding.inflate(LayoutInflater.from(parent.context), parent, false)

                        InnerRoutineFirst4ViewHolder(binding)
                    }
                    5 -> {
                        val binding = ItemRoutineFirst5Binding.inflate(LayoutInflater.from(parent.context), parent, false)

                        InnerRoutineFirst5ViewHolder(binding)
                    }
                    else -> {
                        val binding = ItemRoutineFirst2Binding.inflate(LayoutInflater.from(parent.context), parent, false)

                        InnerRoutineFirst2ViewHolder(binding)
                    }
                }
            }
            innerRoutinesItemList.logArray.size + 1 -> {
                val binding = ItemRoutineLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                InnerRoutineLastViewHolder(binding)
            }
            else -> { // 가운데 사진 홀더 생성
                val binding = ItemRoutineMidBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                InnerRoutineMidViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> {
                when (innerRoutinesItemList.logArray.size) {
                    3 -> {
                        (holder as InnerRoutineFirst3ViewHolder).bind(innerRoutinesItemList)
                    }
                    4 -> {
                        (holder as InnerRoutineFirst4ViewHolder).bind(innerRoutinesItemList)
                    }
                    5 -> {
                        (holder as InnerRoutineFirst5ViewHolder).bind(innerRoutinesItemList)
                    }
                    else -> {
                        (holder as InnerRoutineFirst2ViewHolder).bind(innerRoutinesItemList)
                    }
                }
            }
            innerRoutinesItemList.logArray.size + 1 -> {
                (holder as InnerRoutineLastViewHolder).bind(innerRoutinesItemList)
            }
            else -> { // 가운데 사진 홀더 값 설정
                (holder as InnerRoutineMidViewHolder).bind(innerRoutinesItemList)
            }
        }
    }

    override fun getItemCount(): Int { // 로그 수 + 2 (전체 루트, 타임테이블)
        return innerRoutinesItemList.logArray.size + 2
    }
}