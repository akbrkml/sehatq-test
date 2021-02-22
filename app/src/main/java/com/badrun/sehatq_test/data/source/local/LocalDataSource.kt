package com.badrun.sehatq_test.data.source.local

import com.badrun.sehatq_test.data.source.local.dao.CategoryDao
import com.badrun.sehatq_test.data.source.local.dao.ProductDao
import com.badrun.sehatq_test.data.source.local.entity.CategoryEntity
import com.badrun.sehatq_test.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao
) {

    fun getAllProduct(): List<ProductEntity> = productDao.getAll()

    fun searchProduct(name: String): Flow<List<ProductEntity>> = productDao.getListByTitle(name)

    suspend fun insertProduct(productList: List<ProductEntity>) = productDao.insert(productList)

    fun getAllCategory(): List<CategoryEntity> = categoryDao.getAll()

    suspend fun insertCategory(categoryList: List<CategoryEntity>) =
        categoryDao.insert(categoryList)

}