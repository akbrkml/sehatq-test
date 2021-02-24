package com.badrun.sehatq_test.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.badrun.sehatq_test.base.BaseRecyclerViewAdapter
import com.badrun.sehatq_test.databinding.ItemPromoBinding
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.ui.callback.OnItemClickListener
import com.badrun.sehatq_test.ui.viewholders.PromoHolder

class PromoAdapter(private val onItemClickListener: OnItemClickListener<Product>): BaseRecyclerViewAdapter<PromoHolder>() {

    private val items = mutableListOf<Product>()

    fun setData(items: List<Product>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): PromoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPromoBinding.inflate(layoutInflater, parent, false)
        return PromoHolder(binding)
    }

    override fun onBindViewHolder(holder: PromoHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}