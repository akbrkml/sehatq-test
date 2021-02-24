package com.badrun.sehatq_test.ui.viewholders

import coil.load
import com.badrun.sehatq_test.base.BaseViewHolder
import com.badrun.sehatq_test.databinding.ItemCategoryBinding
import com.badrun.sehatq_test.domain.model.Category

class CategoryHolder(private val binding: ItemCategoryBinding): BaseViewHolder<Category>(binding) {

    override fun bind(item: Category) {
        binding.ivCategory.load(item.imageUrl)
        binding.tvCategoryName.text = item.name
    }

}