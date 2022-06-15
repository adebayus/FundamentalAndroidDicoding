package com.sebade.dicodingsubmissongithubusertwo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebade.dicodingsubmissongithubusertwo.databinding.FragmentRecViewBinding
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.UserItem

class RecViewFragment : Fragment() {

    private lateinit var binding : FragmentRecViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.getParcelableArrayList<UserItem>("LIST")
        val adapter = RecViewAdapter(args as ArrayList<UserItem>)
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUser.adapter = adapter
    }


}