package com.badrun.sehatq_test.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.domain.usecase.SehatQUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PurchasedHistoryViewModel(private val useCase: SehatQUseCase) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    fun getProductProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllProductProduct().collect {
                _products.postValue(it)
            }
        }
    }

}