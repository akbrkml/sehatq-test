package com.badrun.sehatq_test.domain.usecase

import com.badrun.sehatq_test.data.Resource
import com.badrun.sehatq_test.domain.model.Home
import com.badrun.sehatq_test.domain.repository.ISehatQRepository
import kotlinx.coroutines.flow.Flow

class SehatQInteractor(private val sehatQRepository: ISehatQRepository) :
    SehatQUseCase {

    override fun getHome(): Flow<Resource<Home>> {
        return sehatQRepository.getHome()
    }


}