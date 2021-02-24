package com.badrun.sehatq_test.domain.usecase

import com.badrun.sehatq_test.data.Resource
import com.badrun.sehatq_test.domain.model.Category
import com.badrun.sehatq_test.domain.model.Home
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.domain.repository.ISehatQRepository
import kotlinx.coroutines.flow.Flow

class SehatQInteractor(private val sehatQRepository: ISehatQRepository) : SehatQUseCase {

    override fun getHome(): Flow<Resource<Home>> {
        return sehatQRepository.getHome()
    }

    override suspend fun getAllCategory(): Flow<List<Category>> {
        return sehatQRepository.getAllCategory()
    }

    override suspend fun getAllProduct(): Flow<List<Product>> {
        return sehatQRepository.getAllProduct()
    }

    override suspend fun getAllProductProduct(): Flow<List<Product>> {
        return sehatQRepository.getAllProductPurchased()
    }

    override suspend fun searchProduct(keyword: String): Flow<List<Product>> {
        return sehatQRepository.searchProduct(keyword)
    }

    override fun setFavoriteProduct(product: Product, state: Int) {
        sehatQRepository.setFavoriteProduct(product, state)
    }

    override fun addTransaction(productId: String) {
        sehatQRepository.addTransaction(productId)
    }


}