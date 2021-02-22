package com.badrun.sehatq_test.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ProductPromoResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("loved")
    val loved: Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("title")
    val title: String
)