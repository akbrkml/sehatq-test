package com.badrun.sehatq_test.domain.usecase

import com.badrun.sehatq_test.data.Resource
import com.badrun.sehatq_test.domain.model.Home
import kotlinx.coroutines.flow.Flow

interface SehatQUseCase {

    fun getHome(): Flow<Resource<Home>>

}