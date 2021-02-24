package com.badrun.sehatq_test.data.source

import com.badrun.sehatq_test.data.NetworkBoundResource
import com.badrun.sehatq_test.data.Resource
import com.badrun.sehatq_test.data.source.local.LocalDataSource
import com.badrun.sehatq_test.data.source.remote.RemoteDataSource
import com.badrun.sehatq_test.data.source.remote.network.ApiResponse
import com.badrun.sehatq_test.data.source.remote.response.HomeResponse
import com.badrun.sehatq_test.domain.model.Category
import com.badrun.sehatq_test.domain.model.Home
import com.badrun.sehatq_test.domain.model.Product
import com.badrun.sehatq_test.domain.repository.ISehatQRepository
import com.badrun.sehatq_test.utils.AppExecutors
import com.badrun.sehatq_test.utils.mapper.CategoryMapper
import com.badrun.sehatq_test.utils.mapper.ProductMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class SehatQRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISehatQRepository {

    override fun getHome(): Flow<Resource<Home>> =
        object : NetworkBoundResource<Home, HomeResponse>() {
            override fun loadFromDB(): Flow<Home> {
                val home = Home(category = arrayListOf(), productPromo = arrayListOf())
                return flowOf(home)
            }

            override fun shouldFetch(data: Home?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<HomeResponse>> {
                return remoteDataSource.getHome()
            }

            override suspend fun saveCallResult(data: HomeResponse) {
                val productEntities = ProductMapper.mapResponsesToEntities(data.productPromo)
                val categoryEntities = CategoryMapper.mapResponsesToEntities(data.category)
                localDataSource.insertProduct(productEntities)
                localDataSource.insertCategory(categoryEntities)
            }

        }.asFlow()

    override suspend fun getAllCategory(): Flow<List<Category>> {
        return localDataSource.getAllCategory().map { CategoryMapper.mapEntitiesToDomain(it) }
    }

    override suspend fun getAllProduct(): Flow<List<Product>> {
        return localDataSource.getAllProduct().map { ProductMapper.mapEntitiesToDomain(it) }
    }

    override suspend fun getAllProductPurchased(): Flow<List<Product>> {
        return localDataSource.getAllProductPurchased().map { ProductMapper.mapEntitiesToDomain(it) }
    }

    override suspend fun searchProduct(keyword: String): Flow<List<Product>> {
        return localDataSource.searchProduct("%$keyword%").map { ProductMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteProduct(product: Product, state: Int) {
        val productEntity = ProductMapper.mapDomainToEntity(product)
        appExecutors.diskIO().execute { localDataSource.setFavoriteProduct(productEntity, state) }
    }

    override fun addTransaction(productId: String) {
        appExecutors.diskIO().execute { localDataSource.insertTransaction(productId) }
    }

}