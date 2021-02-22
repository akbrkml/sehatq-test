package com.badrun.sehatq_test.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("category")
    val category: List<CategoryResponse>,
    @SerializedName("productPromo")
    val productPromo: List<ProductResponse>
)