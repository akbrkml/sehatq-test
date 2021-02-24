package com.badrun.sehatq_test.ui.viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import com.badrun.sehatq_test.base.BaseViewHolder
import com.badrun.sehatq_test.databinding.ItemMainBinding
import com.badrun.sehatq_test.domain.model.Category
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.ui.callback.OnItemClickListener
import com.badrun.sehatq_test.ui.main.CategoryAdapter
import com.badrun.sehatq_test.ui.main.PromoAdapter

class MainHolder(val binding: ItemMainBinding) : BaseViewHolder<Nothing>(binding) {

    fun setupCategoryNestedRecyclerView(items: List<Category>) {
        val categoryAdapter = CategoryAdapter()
        binding.rvSub.adapter = categoryAdapter
        binding.rvSub.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.setData(items)
    }

    fun setupPromoNestedRecyclerView(items: List<Product>, onItemClickListener: OnItemClickListener<Product>) {
        val promoAdapter = PromoAdapter(onItemClickListener)
        binding.rvSub.adapter = promoAdapter
        binding.rvSub.layoutManager =
            LinearLayoutManager(itemView.context)
        promoAdapter.setData(items)
    }

}
