package com.raka.data

import com.raka.data.database.DBProduct
import com.raka.data.model.ApiResponse
import com.raka.data.model.ProductResponse

object DataTest {
    val apiResponse = ApiResponse(
        total = 3,
        limit = 3,
        skip = 0,
        productResponses = listOf(
            ProductResponse(
                id = 1,
                thumbnail = "https:img.url",
                price = 599,
                description = "description of product",
                category = "smartphone",
                rating = 4.11,
                title = "Iphone 9",
                brand = "brand",
                discountPercentage = 30.0,
                images = listOf(),
                stock = 2
            ),
            ProductResponse(
                id = 2,
                thumbnail = "https:img.url",
                price = 399,
                description = "description of product",
                category = "smartphone",
                rating = 3.3,
                title = "Samsung Galaxy",
                brand = "brand",
                discountPercentage = 30.0,
                images = listOf(),
                stock = 2
            ),
            ProductResponse(
                id = 3,
                thumbnail = "https:img.url",
                price = 499,
                description = "description of product",
                category = "smartphone",
                rating = 4.9,
                title = "Xiaomi",
                brand = "brand",
                discountPercentage = 30.0,
                images = listOf(),
                stock = 2
            ),
            ProductResponse(
                id = 4,
                thumbnail = "https:img.url",
                price = 399,
                description = "description of product",
                category = "smartphone",
                rating = 4.5,
                title = "Nokia",
                brand = "brand",
                discountPercentage = 30.0,
                images = listOf(),
                stock = 2
            )
        )
    )
    val listDBProduct = listOf(
        DBProduct(
            id = 1,
            thumbnail = "https:img.url",
            price = 599,
            description = "description of product",
            category = "smartphone",
            rating = 4.11,
            isFavorite = false,
            title = "Iphone 9",
            brand = "brand",
            discountPercentage = 30.0,
            images = listOf(),
            stock = 2
        ),
        DBProduct(
            id = 2,
            thumbnail = "https:img.url",
            price = 399,
            description = "description of product",
            category = "smartphone",
            rating = 3.3,
            isFavorite = false,
            title = "Samsung Galaxy",
            brand = "brand",
            discountPercentage = 30.0,
            images = listOf(),
            stock = 2
        ),
        DBProduct(
            id = 3,
            thumbnail = "https:img.url",
            price = 499,
            description = "description of product",
            category = "smartphone",
            rating = 4.9,
            isFavorite = false,
            title = "Xiaomi",
            brand = "brand",
            discountPercentage = 30.0,
            images = listOf(),
            stock = 2
        ),
        DBProduct(
            id = 4,
            thumbnail = "https:img.url",
            price = 399,
            description = "description of product",
            category = "smartphone",
            rating = 4.5,
            isFavorite = false,
            title = "Nokia",
            brand = "brand",
            discountPercentage = 30.0,
            images = listOf(),
            stock = 2
        )
    )

    val dbProduct = DBProduct(
        id = 1,
        discountPercentage = 0.0,
        thumbnail = "https:img.url",
        images = listOf(),
        price = 299,
        rating = 2.33,
        description = "description of product",
        title = "iphone 9",
        stock = 2,
        brand = "apple",
        category = "smartphone",
        isFavorite = false
    )
}