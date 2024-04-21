package com.raka.amazonia

import com.raka.amazonia.model.ProductCompact
import com.raka.data.database.DBProduct

object DataTest {
    val expectedProduct = ProductCompact(
        id = 1,
        thumbnail = "https:img.url",
        price = 299,
        rating = 2.33,
        description = "description of product",
        title = "iphone 9",
        category = "smartphone",
        isFavorite = false,
        rank = 3,
        totalProduct = 4
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

    val listProductCompact = listOf(
        ProductCompact(
            id = 1,
            thumbnail = "https:img.url",
            price = 599,
            description = "description of product",
            category = "smartphone",
            rating = 4.11,
            isFavorite = false,
            title = "Iphone 9",
            rank = 0,
            totalProduct = 0
        ),
        ProductCompact(
            id = 2,
            thumbnail = "https:img.url",
            price = 399,
            description = "description of product",
            category = "smartphone",
            rating = 3.3,
            isFavorite = false,
            title = "Samsung Galaxy",
            rank = 0,
            totalProduct = 0
        ),
        ProductCompact(
            id = 3,
            thumbnail = "https:img.url",
            price = 499,
            description = "description of product",
            category = "smartphone",
            rating = 4.9,
            isFavorite = false,
            title = "Xiaomi",
            rank = 0,
            totalProduct = 0
        ),
        ProductCompact(
            id = 4,
            thumbnail = "https:img.url",
            price = 399,
            description = "description of product",
            category = "smartphone",
            rating = 4.5,
            isFavorite = false,
            title = "Nokia",
            rank = 0,
            totalProduct = 0
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