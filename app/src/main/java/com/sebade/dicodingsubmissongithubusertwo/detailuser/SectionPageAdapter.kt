package com.sebade.dicodingsubmissongithubusertwo.detailuser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sebade.dicodingsubmissongithubusertwo.RecViewFragment
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.UserItem

class SectionPageAdapter(private var activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private var listFollower : MutableList<UserItem> = mutableListOf()
    private var listFollowing : MutableList<UserItem> = mutableListOf()


    fun setlistFollower(list: List<UserItem>) {
        Log.d("TAG", "setlistFollower: before ${this.listFollower} ")
        this.listFollower = list as MutableList<UserItem>
        Log.d("TAG", "setlistFollower: after ${this.listFollower} ")
        notifyDataSetChanged()
    }

    fun setlistFollowing(list: List<UserItem>) {
        this.listFollowing = list as MutableList<UserItem>
        Log.d("TAG", "setlistFollowing: asdasd")
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = RecViewFragment()
        when (position) {
            0 -> {
                fragment.arguments = Bundle().apply {
                    if (listFollower.isNotEmpty()) {
                        putParcelableArrayList("LIST", listFollower as ArrayList<UserItem>)
                    }
                }
            }
            1 -> {
                fragment.arguments = Bundle().apply {
                    if (listFollowing.isNotEmpty()) {
                        putParcelableArrayList("LIST", listFollowing as ArrayList<UserItem>)
                    }
                }
            }
        }
        return fragment
    }

}