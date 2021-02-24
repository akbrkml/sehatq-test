package com.badrun.sehatq_test.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val productId: String
) {
    constructor(productId: String) : this(0, productId)
}