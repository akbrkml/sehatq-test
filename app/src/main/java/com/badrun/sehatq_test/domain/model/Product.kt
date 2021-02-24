package com.badrun.sehatq_test.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Product(
    val id: String,
    val description: String,
    val imageUrl: String,
    val loved: Int,
    val price: String,
    val title: String
): Parcelable