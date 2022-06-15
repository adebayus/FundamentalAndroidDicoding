package com.sebade.dicodingsubmissongithubusertwo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebade.dicodingsubmissongithubusertwo.databinding.ItemUserFollowBinding
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.UserItem

class RecViewAdapter(val list : ArrayList<UserItem>) : RecyclerView.Adapter<RecViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemUserFollowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item : UserItem){
            Glide.with(binding.circularImageView3)
                .load(item.avatar_url)
                .into(binding.circularImageView3)
            binding.fullName.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}