package com.sebade.dicodingsubmissongithubusertwo.detailuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sebade.dicodingsubmissongithubusertwo.databinding.FragmentDetailUserBinding

class DetailUserFragment : Fragment() {

    private val viewModel by viewModels<DetailUserViewModel>()
    private var binding: FragmentDetailUserBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailUserBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailUserFragmentArgs.fromBundle(requireArguments()).user
        viewModel.getUserDetail(args)
        initViewPager()
        initBasicDetail()
    }

    private fun initBasicDetail() {
        viewModel.userDetail.observe(viewLifecycleOwner) {
            if (it != null) {
                with(binding!!) {
                    progressBar.visibility = View.GONE
                    Glide.with(civAvatar)
                        .load(it.avatarUrl)
                        .into(civAvatar)
                    civAvatar.visibility = View.VISIBLE
                    tvFullname.text = it.name
                    tvUsername.text = it.login
                    tvRepo.text = StringBuilder().append(it.publicRepos).append(" Repos")
                    tvFollower.text = StringBuilder().append(it.followers).append(" Followers")
                    tvFollowing.text = StringBuilder().append(it.following).append(" Following")
                    tvFullname.visibility = View.VISIBLE
                    tvUsername.visibility = View.VISIBLE
                    tvRepo.visibility = View.VISIBLE
                    tvFollower.visibility = View.VISIBLE
                    tvFollowing.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initViewPager() {

        val sectionPageAdapter = SectionPageAdapter(requireActivity())

        viewModel.follower.observe(viewLifecycleOwner) {
            sectionPageAdapter.setlistFollower(it)

        }
        viewModel.following.observe(viewLifecycleOwner) {
            sectionPageAdapter.setlistFollowing(it)
        }
        val viewPager: ViewPager2 = binding?.viewPager!!
        val tabs: TabLayout = binding?.tabLayout!!

        viewModel.listFollow.observe(viewLifecycleOwner) {
            if (!it.follower.isNullOrEmpty() && !it.following.isNullOrEmpty()) {
                sectionPageAdapter.setlistFollowing(it.following)
                sectionPageAdapter.setlistFollower(it.follower)
                viewPager.adapter = sectionPageAdapter
                TabLayoutMediator(tabs, viewPager) { tab, position ->
                    tab.text = listTitleTab[position]
                }.attach()
            }
        }


    }

    companion object {
        val listTitleTab = listOf<String>(
            "Follower",
            "Following"
        )

    }
}