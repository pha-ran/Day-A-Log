package com.day_a_log.src.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.day_a_log.R
import com.day_a_log.databinding.ItemRoutineBinding
import com.day_a_log.src.main.home.models.RoutinesResult

class HomeAdaptor(private val routinesItemList : List<RoutinesResult>, private val gm : RequestManager, private val context : Context)
    : RecyclerView.Adapter<HomeAdaptor.HomeViewHolder>() {
    //ToDo 진행중 (생명주기 고려 새로고침 추가)

    inner class HomeViewHolder(private val binding: ItemRoutineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RoutinesResult) {
            binding.tvId.text = data.userId
            binding.tvTime.text = data.updatedAgo

            gm.load(data.imgUrl)
                .thumbnail(0.1f)
                .error(R.drawable.ic_bnv_menu_profile_x)
                .into(binding.ivProfileImage)

            val innerRv = InnerRoutineAdaptor(data)
            binding.rv.adapter = innerRv
            binding.rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            if (binding.rv.onFlingListener == null) {   // 중복 적용시 오류
                PagerSnapHelper().attachToRecyclerView(binding.rv)
            }
            innerRv.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemRoutineBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(routinesItemList[position])
    }

    override fun getItemCount(): Int {
        return routinesItemList.size
    }
}