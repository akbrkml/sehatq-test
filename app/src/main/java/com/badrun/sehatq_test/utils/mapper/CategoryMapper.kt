package com.badrun.sehatq_test.utils.mapper

import com.badrun.sehatq_test.data.source.local.entity.CategoryEntity
import com.badrun.sehatq_test.data.source.remote.response.CategoryResponse
import com.badrun.sehatq_test.domain.model.Category

object CategoryMapper {

    fun mapResponsesToEntities(input: List<CategoryResponse>): List<CategoryEntity> {
        val categoryList = ArrayList<CategoryEntity>()
        input.map {
            val category = CategoryEntity(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl
            )
            categoryList.add(category)
        }
        return categoryList
    }

    fun mapEntitiesToDomain(input: List<CategoryEntity>): List<Category> =
        input.map {
            Category(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl
            )
        }

}