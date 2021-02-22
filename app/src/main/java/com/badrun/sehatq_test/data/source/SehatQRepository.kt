package com.badrun.sehatq_test.data.source

import com.badrun.sehatq_test.data.NetworkBoundResource
import com.badrun.sehatq_test.data.Resource
import com.badrun.sehatq_test.data.source.local.LocalDataSource
import com.badrun.sehatq_test.data.source.remote.RemoteDataSource
import com.badrun.sehatq_test.data.source.remote.network.ApiResponse
import com.badrun.sehatq_test.data.source.remote.response.HomeResponse
import com.badrun.sehatq_test.domain.model.Home
import com.badrun.sehatq_test.domain.repository.ISehatQRepository
import com.badrun.sehatq_test.utils.mapper.CategoryMapper
import com.badrun.sehatq_test.utils.mapper.ProductMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SehatQRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ISehatQRepository {

    override fun getHome(): Flow<Resource<Home>> =
        object : NetworkBoundResource<Home, HomeResponse>() {
            override fun loadFromDB(): Flow<Home> {
                val categoryList = CategoryMapper.mapEntitiesToDomain(localDataSource.getAllCategory())
                val productList = ProductMapper.mapEntitiesToDomain(localDataSource.getAllProduct())
                val home = Home(category = categoryList, productPromo = productList)
                return flowOf(home)
            }

            override fun shouldFetch(data: Home?): Boolean = data == null

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

}