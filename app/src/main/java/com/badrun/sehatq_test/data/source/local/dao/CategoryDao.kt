package com.badrun.sehatq_test.data.source.local.dao

import androidx.room.*
import com.badrun.sehatq_test.data.source.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryList: List<CategoryEntity>)

}