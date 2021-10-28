package com.day_a_log.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.day_a_log.R
import com.day_a_log.databinding.ItemRoutineBinding
import com.day_a_log.src.main.home.models.Result

class HomeAdaptor(private val routinesItemList : List<Result>, private val gm : RequestManager) : RecyclerView.Adapter<HomeAdaptor.HomeViewHolder>() {
    //ToDo 진행중

    inner class HomeViewHolder(private val binding: ItemRoutineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Result) {
            gm.load(data.imgUrl)
                .thumbnail(0.1f)
                .error(R.drawable.ic_bnv_menu_profile_x)
                .into(binding.ivProfileImage)
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