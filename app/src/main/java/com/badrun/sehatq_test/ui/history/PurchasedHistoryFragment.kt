package com.badrun.sehatq_test.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.badrun.sehatq_test.base.BaseFragment
import com.badrun.sehatq_test.databinding.PurchasedHistoryFragmentBinding
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.ui.callback.OnItemClickListener
import com.badrun.sehatq_test.ui.search.ProductAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PurchasedHistoryFragment : BaseFragment<PurchasedHistoryFragmentBinding>() {

    private val viewModel: PurchasedHistoryViewModel by viewModel()

    private lateinit var productAdapter: ProductAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> PurchasedHistoryFragmentBinding
        get() = PurchasedHistoryFragmentBinding::inflate

    override fun onCreated(view: View) {
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getProductProduct()
        viewModel.products.observe(viewLifecycleOwner, {
            productAdapter.clear()
            productAdapter.setData(it)
        })
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(onItemClickListener)
        binding.rvHistory.adapter = productAdapter
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
    }

    private val onItemClickListener = object : OnItemClickListener<Product> {
        override fun onClick(item: Product) {
            val action = PurchasedHistoryFragmentDirections.actionHistoryToDetails(item)
            findNavController().navigate(action)
        }

    }


}