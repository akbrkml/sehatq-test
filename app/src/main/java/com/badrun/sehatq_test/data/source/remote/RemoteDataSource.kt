package com.badrun.sehatq_test.data.source.remote

import android.util.Log
import com.badrun.sehatq_test.data.source.remote.network.ApiResponse
import com.badrun.sehatq_test.data.source.remote.network.ApiService
import com.badrun.sehatq_test.data.source.remote.response.HomeResponse
import com.badrun.sehatq_test.domain.model.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getHome(): Flow<ApiResponse<List<DataResult<HomeResponse>>>> {
        return flow {
            try {
                val response = apiService.getHome()
                when(response.code()) {
                    200 -> {
                        val dataArray = response.body()
                        if (dataArray != null && dataArray.isNotEmpty()) {
                            emit(ApiResponse.Success(dataArray))
                        } else {
                            emit(ApiResponse.Empty)
                        }
                    }
                    else -> {
                        emit(ApiResponse.Error("Unable to load API"))
                    }

                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}

