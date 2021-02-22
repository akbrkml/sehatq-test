package com.badrun.sehatq_test.domain.model


data class Product(
    val id: String,
    val description: String,
    val imageUrl: String,
    val loved: Int,
    val price: String,
    val title: String
)