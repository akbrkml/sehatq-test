package com.badrun.sehatq_test.data.source.local

import com.badrun.sehatq_test.data.source.local.dao.CategoryDao
import com.badrun.sehatq_test.data.source.local.dao.ProductDao
import com.badrun.sehatq_test.data.source.local.dao.TransactionDao
import com.badrun.sehatq_test.data.source.local.entity.CategoryEntity
import com.badrun.sehatq_test.data.source.local.entity.ProductEntity
import com.badrun.sehatq_test.data.source.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao,
    private val transactionDao: TransactionDao
) {

    fun getAllProduct(): Flow<List<ProductEntity>> = productDao.getAll()

    fun searchProduct(name: String): Flow<List<ProductEntity>> = productDao.getListByTitle(name)

    suspend fun insertProduct(productList: List<ProductEntity>) = productDao.insert(productList)

    fun getAllCategory(): Flow<List<CategoryEntity>> = categoryDao.getAll()

    suspend fun insertCategory(categoryList: List<CategoryEntity>) =
        categoryDao.insert(categoryList)

    fun insertTransaction(productId: String) {
        val transactionEntity = TransactionEntity(productId)
        transactionDao.insert(transactionEntity)
    }

    fun setFavoriteProduct(product: ProductEntity, newState: Int) {
        product.loved = newState
        productDao.updateFavoriteProduct(product)
    }

    fun getAllProductPurchased(): Flow<List<ProductEntity>> = transactionDao.getAll()

}