package com.badrun.sehatq_test.domain.repository

import com.badrun.sehatq_test.data.Resource
import com.badrun.sehatq_test.domain.model.DataResult
import com.badrun.sehatq_test.domain.model.Home
import kotlinx.coroutines.flow.Flow

interface ISehatQRepository {

    fun getHome(): Flow<Resource<Home>>

}