package com.badrun.sehatq_test.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.badrun.sehatq_test.base.BaseRecyclerViewAdapter
import com.badrun.sehatq_test.databinding.ItemCategoryBinding
import com.badrun.sehatq_test.domain.model.Category
import com.badrun.sehatq_test.ui.viewholders.CategoryHolder

class CategoryAdapter: BaseRecyclerViewAdapter<CategoryHolder>() {

    private val items = mutableListOf<Category>()

    fun setData(items: List<Category>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}