package com.badrun.sehatq_test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.badrun.sehatq_test.R
import com.badrun.sehatq_test.base.BaseFragment
import com.badrun.sehatq_test.data.Resource
import com.badrun.sehatq_test.databinding.MainFragmentBinding
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.ui.callback.OnItemClickListener
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainFragmentBinding>(), View.OnClickListener {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var mainAdapter: MainAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainFragmentBinding
        get() = MainFragmentBinding::inflate

    override fun onCreated(view: View) {
        initViews()
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.home.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    loadHome()
                }
                is Resource.Empty -> {
                    hideProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
            }
        })

        viewModel.categories.observe(viewLifecycleOwner, {
            mainAdapter.setCategory(it)
        })

        viewModel.products.observe(viewLifecycleOwner, {
            mainAdapter.setProduct(it)
        })
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter(onItemClickListener)
        binding.rvMain.adapter = mainAdapter
        binding.rvMain.layoutManager = LinearLayoutManager(requireContext())
    }

    private val onItemClickListener = object : OnItemClickListener<Product> {
        override fun onClick(item: Product) {
            val action = MainFragmentDirections.actionMainToDetails(item)
            findNavController().navigate(action)
        }

    }

    private fun initViews() {
        binding.searchView.setOnClickListener(this)
    }

    private fun loadHome() {
        viewModel.getCategory()
        viewModel.getProduct()
    }

    private fun hideProgressBar() {
        binding.pbLoading.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.pbLoading.visibility = View.VISIBLE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.search_view -> {
                val action = MainFragmentDirections.actionMainToSearch()
                findNavController().navigate(action)
            }
        }
    }

}