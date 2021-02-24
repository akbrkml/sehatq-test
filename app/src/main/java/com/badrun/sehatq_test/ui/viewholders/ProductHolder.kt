package com.badrun.sehatq_test.ui.viewholders

import coil.load
import com.badrun.sehatq_test.base.BaseViewHolder
import com.badrun.sehatq_test.databinding.ItemProductBinding
import com.badrun.sehatq_test.domain.model.Product

class ProductHolder(private val binding: ItemProductBinding): BaseViewHolder<Product>(binding) {

    override fun bind(item: Product) {
        binding.ivProductImage.load(item.imageUrl)
        binding.tvProductTitle.text = item.title
        binding.tvProductPrice.text = item.price
    }

}
