package com.raka.amazonia.utils

import com.raka.amazonia.data.model.ProductCompact
import com.raka.data.database.DBProduct

fun DBProduct.toProductCompact(dbProduct: DBProduct): ProductCompact {
    return ProductCompact(
        id = dbProduct.id,
        thumbnail = dbProduct.thumbnail,
        price = dbProduct.price,
        description = dbProduct.description,
        title = dbProduct.title,
        isFavorite = dbProduct.isFavorite,
        category = dbProduct.category,
        rating = dbProduct.rating
    )
}