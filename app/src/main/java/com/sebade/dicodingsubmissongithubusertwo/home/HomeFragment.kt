package com.sebade.dicodingsubmissongithubusertwo.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sebade.dicodingsubmissongithubusertwo.R
import com.sebade.dicodingsubmissongithubusertwo.databinding.FragmentHomeBinding
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.UserItem
import androidx.appcompat.widget.SearchView as xSearchView

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeFragmentViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenu()
        initRvUser()
        initProgresBar()
        Log.d("TAG", "onViewCreated: created ")
    }

    private fun initProgresBar() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it == true) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun initRvUser() {
        val listUserAdapter = ListUserAdapter()
        listUserAdapter.setOnClickListener(object : ListUserAdapter.IOnClickListener {
            override fun onClick(item : UserItem) {
                requireView().findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailUserFragment(item))
            }

        })
        binding.rvUserListItem.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvUserListItem.adapter = listUserAdapter
        viewModel._userItem.observe(viewLifecycleOwner) {
            listUserAdapter.setListUser(it)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.rvUserListItem.visibility = if (it) View.GONE else View.VISIBLE
        }

    }

    private fun initMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)
                val searchView = menu.findItem(R.id.search).actionView as xSearchView
                val searchManager =
                    requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
                searchView.maxWidth = Int.MAX_VALUE
                searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
                searchView.setOnQueryTextListener(object : xSearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return if (query != null) {
                            viewModel.searchUser(query)
                            true
                        } else {
                            false
                        }
                    }

                    override fun onQueryTextChange(query: String?): Boolean {
                        return true
                    }

                })
                searchView.setOnQueryTextFocusChangeListener { view, b ->
                    if (!b) {
                        viewModel.getListUser()
                    }
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }


}