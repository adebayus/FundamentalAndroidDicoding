package com.sebade.dicodingsubmissongithubusertwo.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebade.dicodingsubmissongithubusertwo.databinding.ItemUserLayoutBinding
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.UserItem

class ListUserAdapter() : RecyclerView.Adapter<ListUserAdapter.ViewHolder>() {

    private lateinit var onClickListener: IOnClickListener
    private var listUser: List<UserItem>? = listOf<UserItem>()

    inner class ViewHolder(private var binding: ItemUserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserItem) {
            binding.tvUsername.text = item?.login
            Glide.with(binding.circularImageView2)
                .load(item?.avatar_url)
                .into(binding.circularImageView2)
            itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
        }
    }

    fun setOnClickListener(onClickListener: IOnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listUser?.let { holder.bind(it.get(position)) }
    }

    override fun getItemCount(): Int = listUser?.size ?: 0

    fun setListUser(listUser: List<UserItem>?) {
        this.listUser = listUser
        notifyDataSetChanged()
    }

    interface IOnClickListener {
        fun onClick(user : UserItem)
    }
}