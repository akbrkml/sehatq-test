package com.badrun.sehatq_test.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.badrun.sehatq_test.data.source.local.dao.CategoryDao
import com.badrun.sehatq_test.data.source.local.dao.ProductDao
import com.badrun.sehatq_test.data.source.local.entity.CategoryEntity
import com.badrun.sehatq_test.data.source.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SehatQDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao

}