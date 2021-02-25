package com.badrun.sehatq_test.data.source.remote

import com.badrun.sehatq_test.data.source.remote.response.CategoryResponse

object CategoryData {

    val categories = listOf(
        CategoryResponse(
            imageUrl = "https://img.icons8.com/bubbles/2x/t-shirt.png",
            id = 21,
            name = "Baju"
        ),
        CategoryResponse(
            imageUrl = "https://img.icons8.com/flat_round/2x/long-shorts.png",
            id = 42,
            name = "Celana"
        ),
        CategoryResponse(
            imageUrl = "https://img.icons8.com/flat_round/2x/summer-hat.png",
            id = 11,
            name = "Topi"
        ),
        CategoryResponse(
            imageUrl = "https://img.icons8.com/color/2x/red-purse.png",
            id = 91,
            name = "Tas"
        ),
        CategoryResponse(
            imageUrl = "https://img.icons8.com/cute-clipart/2x/apple-watch-apps.png",
            id = 131,
            name = "Jam Tangan"
        )
    )

}