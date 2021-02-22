package com.badrun.sehatq_test.data.source.remote.network

import com.badrun.sehatq_test.data.source.remote.response.HomeResponse
import com.badrun.sehatq_test.domain.model.DataResult
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET(ENDPOINT_HOME)
    suspend fun getHome(): Response<List<DataResult<HomeResponse>>>

}
