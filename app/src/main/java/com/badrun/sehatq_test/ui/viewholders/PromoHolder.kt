package com.badrun.sehatq_test.ui.viewholders

import coil.load
import com.badrun.sehatq_test.R
import com.badrun.sehatq_test.base.BaseViewHolder
import com.badrun.sehatq_test.databinding.ItemPromoBinding
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.ui.callback.OnItemClickListener

class PromoHolder(private val binding: ItemPromoBinding) : BaseViewHolder<Product>(binding) {

    override fun bind(item: Product) {
        binding.ivProductPromo.load(item.imageUrl)
        binding.imgBtnLoved.load(getFavoriteResource(item.loved))
        binding.tvProductTitle.text = item.title
    }

    private fun getFavoriteResource(loved: Int): Int {
        return if (loved == 0) R.drawable.ic_favorite
        else R.drawable.ic_favorite_selected
    }

}
