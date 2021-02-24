package com.badrun.sehatq_test.domain.repository

import com.badrun.sehatq_test.data.Resource
import com.badrun.sehatq_test.domain.model.Category
import com.badrun.sehatq_test.domain.model.DataResult
import com.badrun.sehatq_test.domain.model.Home
import com.badrun.sehatq_test.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ISehatQRepository {

    fun getHome(): Flow<Resource<Home>>

    suspend fun getAllCategory(): Flow<List<Category>>

    suspend fun getAllProduct(): Flow<List<Product>>

    suspend fun getAllProductPurchased(): Flow<List<Product>>

    suspend fun searchProduct(keyword: String): Flow<List<Product>>

    fun setFavoriteProduct(product: Product, state: Int)

    fun addTransaction(productId: String)

}