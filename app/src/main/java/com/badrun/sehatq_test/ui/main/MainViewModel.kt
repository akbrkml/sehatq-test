package com.badrun.sehatq_test.ui.main

import androidx.lifecycle.*
import com.badrun.sehatq_test.domain.model.Category
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.domain.usecase.SehatQUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: SehatQUseCase) : ViewModel() {

    val home = useCase.getHome().asLiveData()


    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllCategory().collect {
                _categories.postValue(it)
            }
        }
    }

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    fun getProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllProduct().collect {
                _products.postValue(it)
            }
        }
    }

}