package com.badrun.sehatq_test.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.domain.usecase.SehatQUseCase

class ProductDetailViewModel(private val useCase: SehatQUseCase) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    fun setProduct(product: Product) {
        _product.value = product
    }

    fun setFavoriteProduct(product: Product, newStatus: Boolean) {
        val status = if (newStatus) 1 else 0
        useCase.setFavoriteProduct(product, status)
    }

    fun addTransaction(productId: String) = useCase.addTransaction(productId)
}