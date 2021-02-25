package com.badrun.sehatq_test.data.source.remote

import com.badrun.sehatq_test.data.source.remote.network.ApiResponse
import com.badrun.sehatq_test.data.source.remote.network.ApiService
import com.badrun.sehatq_test.data.source.remote.response.HomeResponse
import com.badrun.sehatq_test.domain.model.DataResult
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.collect
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import retrofit2.Response

class RemoteDataSourceTest {

    private val apiService = mock<ApiService>()
    private val remoteDataSource = RemoteDataSource(apiService)

    @Test
    fun `flow emits successfully`() = runBlocking {

        // Mock API Service
        val homeResponse = HomeResponse(
            category = CategoryData.categories, productPromo = ProductData.products
        )
        val response: Response<List<DataResult<HomeResponse>>> = Response.success(listOf(DataResult(homeResponse)))
        apiService.stub {
            onBlocking { getHome() } doReturn response
        }
        // Test
        val flow = remoteDataSource.getHome()

        // Verify
        flow.collect { result ->
            when (result) {
                is ApiResponse.Success -> {
                    result.data shouldBeEqualTo homeResponse
                }
            }
        }

    }

}