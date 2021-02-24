package com.badrun.sehatq_test.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.badrun.sehatq_test.R
import com.badrun.sehatq_test.base.BaseFragment
import com.badrun.sehatq_test.databinding.ProductDetailFragmentBinding
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.utils.ext.share
import org.koin.android.viewmodel.ext.android.viewModel

class ProductDetailFragment : BaseFragment<ProductDetailFragmentBinding>(), View.OnClickListener {

    private val viewModel: ProductDetailViewModel by viewModel()

    private var isFavorite = false

    private lateinit var product: Product

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ProductDetailFragmentBinding
        get() = ProductDetailFragmentBinding::inflate

    override fun onCreated(view: View) {
        initViews()

        val product = ProductDetailFragmentArgs.fromBundle(requireArguments()).product
        viewModel.setProduct(product)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.product.observe(viewLifecycleOwner, {
            product = it
            populateDataProduct(it)
        })
    }

    private fun populateDataProduct(item: Product) {
        binding.ivProductImage.load(item.imageUrl)
        binding.tvProductTitle.text = item.title
        binding.tvProductDescription.text = item.description
        binding.tvProductPrice.text = item.price
        val status = item.loved == 1
        setStatusFavorite(status)
    }

    private fun initViews() {
        binding.imgBtnShare.setOnClickListener(this)
        binding.imgBtnLoved.setOnClickListener(this)
        binding.btnBuy.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.img_btn_share ->
                if (this::product.isInitialized) requireActivity().share(product.title, product.price)
            R.id.img_btn_loved -> {
                if (this::product.isInitialized) {
                    isFavorite = !isFavorite
                    viewModel.setFavoriteProduct(product, isFavorite)
                    setStatusFavorite(isFavorite)
                }
            }
            R.id.btn_buy -> if (this::product.isInitialized) viewModel.addTransaction(productId = product.id)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) binding.imgBtnLoved.load(R.drawable.ic_favorite_selected)
        else binding.imgBtnLoved.load(R.drawable.ic_favorite)
    }

}