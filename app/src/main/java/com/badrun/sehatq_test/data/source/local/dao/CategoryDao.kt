package com.badrun.sehatq_test.data.source.local.dao

import androidx.room.*
import com.badrun.sehatq_test.data.source.local.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAll(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryList: List<CategoryEntity>)

}