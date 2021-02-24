package com.badrun.sehatq_test.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.badrun.sehatq_test.data.source.local.entity.ProductEntity
import com.badrun.sehatq_test.data.source.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM product INNER JOIN `transaction` ON `transaction`.productId = product.id")
    fun getAll(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(transaction: TransactionEntity)

}