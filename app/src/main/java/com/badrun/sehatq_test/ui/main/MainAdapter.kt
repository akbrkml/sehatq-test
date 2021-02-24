package com.badrun.sehatq_test.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badrun.sehatq_test.base.BaseRecyclerViewAdapter
import com.badrun.sehatq_test.databinding.ItemMainBinding
import com.badrun.sehatq_test.domain.model.Category
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.ui.callback.OnItemClickListener
import com.badrun.sehatq_test.ui.viewholders.MainHolder

class MainAdapter(private val onItemClickListener: OnItemClickListener<Product>): BaseRecyclerViewAdapter<RecyclerView.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var categories = mutableListOf<Category>()
    private var products = mutableListOf<Product>()

    fun setCategory(items: List<Category>) {
        this.categories.clear()
        this.categories.addAll(items)
        notifyDataSetChanged()
    }

    fun setProduct(items: List<Product>) {
        this.products.clear()
        this.products.addAll(items)
        notifyDataSetChanged()
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            CATEGORY_DATA, PROMO_DATA -> {
                val binding = ItemMainBinding.inflate(layoutInflater, parent, false)
                MainHolder(binding)
            }
            else -> throw IllegalArgumentException("Un support type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            CATEGORY_DATA -> {
                holder as MainHolder
                holder.binding.rvSub.setRecycledViewPool(viewPool)
                holder.setupCategoryNestedRecyclerView(categories)
            }
            PROMO_DATA -> {
                holder as MainHolder
                holder.binding.rvSub.setRecycledViewPool(viewPool)
                holder.setupPromoNestedRecyclerView(products, onItemClickListener)
            }
        }
    }

    override fun getItemCount(): Int = 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> CATEGORY_DATA
            1 -> PROMO_DATA
            else -> throw IllegalArgumentException("Un support type")
        }
    }

    companion object {
        const val PROMO_DATA = 0
        const val CATEGORY_DATA = 1
    }
}